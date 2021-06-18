package com.hobarb.internshalanotes.ui.fragments

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hobarb.internshalanotes.R
import com.hobarb.internshalanotes.databinding.FragmentAddUpdateNoteBinding
import com.hobarb.internshalanotes.db_name.DbManager
import com.hobarb.internshalanotes.utils.Constants

class AddUpdateNoteFragment : Fragment(R.layout.fragment_add_update_note) {
    private var _binding: FragmentAddUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private val args: AddUpdateNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAddUpdateNoteBinding.bind(view)
        val alertCode = args.alertCode
        val thatNoteId = args.thatNoteId
        val thatNoteTitle = args.thatNoteTitle
        val thatNoteDescription = args.thatNoteDescription

        binding.tvAddOrUpdateFragAddUpdate.text = "$thatNoteId"
        binding.tilNoteTitleFragAddUpdate.editText!!.setText(thatNoteTitle)
        binding.tilNoteDescriptionFragAddUpdate.editText!!.setText(thatNoteDescription)
        binding.btnSaveBtnFragAddUpdate.setOnClickListener {
            validateAndSave(alertCode)
        }
    }

    private fun validateAndSave(alertCode: Int) {
        if (binding.etNoteTitleFragAddUpdate.text.isNullOrEmpty()) {
            binding.etNoteTitleFragAddUpdate.error = "Please write a title for your note"
            return;
        }
        if (binding.etNoteDescriptionFragAddUpdate.text.isNullOrEmpty()) {
            binding.etNoteDescriptionFragAddUpdate.error =
                "Please write a description for your note"
            return
        }
        when (alertCode) {
            Constants.alertDialogAddNote -> {
                createNote()
                Toast.makeText(context, "Note created", Toast.LENGTH_SHORT).show()
            }

            Constants.alertDialogUpdateNote -> {
                updateNote()
                Toast.makeText(context, "Note updated", Toast.LENGTH_SHORT).show()
            }
        }
        val action = AddUpdateNoteFragmentDirections.actionAddUpdateNoteFragmentToNotesFragment()
        findNavController().navigate(action)
    }

    private fun updateNote() {
        val dbManager = DbManager(requireContext())
        getContentValues()
        val values = getContentValues()
        val noteId = binding.tvAddOrUpdateFragAddUpdate.text.toString()
        val selectionArgs = arrayOf(noteId)
        var id = dbManager.updateNote(values, "${Constants.colNameNoteId}=?", selectionArgs)

    }

    private fun createNote() {
        val dbManager = DbManager(requireContext())
        getContentValues()
        val values = getContentValues()
        var id = dbManager.insertIntoDb(values)
    }

    private fun getContentValues(): ContentValues {
        val noteId = binding.tvAddOrUpdateFragAddUpdate.text.toString().toLong()
        val noteTitle = binding.etNoteTitleFragAddUpdate.text.toString()
        val noteDescription = binding.etNoteDescriptionFragAddUpdate.text.toString()

        val values = ContentValues()
        values.put(Constants.colNameNoteId, noteId)
        values.put(Constants.colNameNoteTitle, noteTitle)
        values.put(Constants.colNameNoteDescription, noteDescription)

        return values
    }
}

