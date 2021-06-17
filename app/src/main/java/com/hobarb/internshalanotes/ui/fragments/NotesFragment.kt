package com.hobarb.internshalanotes.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hobarb.internshalanotes.R
import com.hobarb.internshalanotes.adapters.NotesAdapter
import com.hobarb.internshalanotes.db_name.DbManager
import com.hobarb.internshalanotes.models.NotesModel
import com.hobarb.internshalanotes.utils.Constants
import com.hobarb.internshalanotes.utils.HelperFunctions

class NotesFragment: Fragment(R.layout.fragment_notes) {
    private lateinit var addNoteFab: FloatingActionButton
    private lateinit var allNotesRv: RecyclerView
    private val col_note_id = Constants.colNameNoteId
    private val col_note_title = Constants.colNameNoteTitle
    private val col_note_description = Constants.colNameNoteDescription
    private var allNotes = ArrayList<NotesModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addNoteFab = view.findViewById(R.id.fab_addNote_fragNotes)
        addNoteFab.setOnClickListener {
            val action = NotesFragmentDirections.actionNotesFragmentToAddUpdateNoteFragment()
            findNavController().navigate(action)
        }
        allNotesRv = view.findViewById(R.id.rv_allNotes_fragNotes)
    }

    override fun onResume() {
        super.onResume()
        LoadQuery("%")
        setupAndLoadRecyclerView()
    }

    private fun setupAndLoadRecyclerView() {
        if(allNotes.isEmpty())
        {
            requireView().findViewById<TextView>(R.id.tv_noNotesAlert_fragNotes).visibility = View.VISIBLE
        }
        else{
            var notesAdapter = NotesAdapter(requireContext(), allNotes)
            allNotesRv.apply {
                adapter = notesAdapter
                layoutManager = LinearLayoutManager(activity)
            }
            notesAdapter.notifyDataSetChanged()
        }


    }

    private fun LoadQuery(title: String) {
        var dbManager = DbManager(requireContext())
        val projection = arrayOf(col_note_id, col_note_title, col_note_description)
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.queryFromDb(projection, "${col_note_title} LIKE ?", selectionArgs, col_note_title)
        allNotes.clear()
        if(cursor!!.moveToFirst())
        {
            do{
                val note_id = cursor.getLong(cursor.getColumnIndex(col_note_id))
                val note_title = cursor.getString(cursor.getColumnIndex(col_note_title))
                val note_description = cursor.getString(cursor.getColumnIndex(col_note_description))
                allNotes.add(NotesModel(note_id, note_title, note_description))
            }while (cursor.moveToNext())
        }

        Toast.makeText(context, "Viewing ${allNotes.size} notes", Toast.LENGTH_SHORT).show()
    }
}