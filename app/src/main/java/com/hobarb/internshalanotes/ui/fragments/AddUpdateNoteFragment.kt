package com.hobarb.internshalanotes.ui.fragments

import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hobarb.internshalanotes.R
import com.hobarb.internshalanotes.databinding.FragmentAddUpdateNoteBinding
import com.hobarb.internshalanotes.db_name.DbManager
import com.hobarb.internshalanotes.utils.Constants

class AddUpdateNoteFragment : Fragment(R.layout.fragment_add_update_note) {
    private var _binding: FragmentAddUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private var alertCode = 1001

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
        //val alertCode = getfroom intent
       binding.btnSaveBtnFragAddUpdate.setOnClickListener {
           when (alertCode) {
               Constants.alertDialogAddNote -> {
                   createNote()
                   Toast.makeText(context, "Note created", Toast.LENGTH_SHORT).show()
               }

               Constants.alertDialogUpdateNote -> {
                   Toast.makeText(context, "Note updated", Toast.LENGTH_SHORT).show()
               }
           }
           val action = AddUpdateNoteFragmentDirections.actionAddUpdateNoteFragmentToNotesFragment()
           findNavController().navigate(action)
       }
    }

    private fun createNote() {
        val dbManager = DbManager(requireContext())
        val noteId = System.currentTimeMillis()
        val noteTitle = binding.etNoteTitleFragAddUpdate.text.toString()
        val noteDescription = binding.etNoteDescriptionFragAddUpdate.text.toString()

        val values = ContentValues()
        values.put(Constants.colNameNoteId, noteId)
        values.put(Constants.colNameNoteTitle, noteTitle)
        values.put(Constants.colNameNoteDescription, noteDescription)

        var id = dbManager.insertIntoDb(values)
    }
}

