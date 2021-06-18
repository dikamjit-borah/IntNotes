package com.hobarb.internshalanotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hobarb.internshalanotes.models.NotesModel
import com.hobarb.internshalanotes.R
import com.hobarb.internshalanotes.databinding.ItemNoteBinding
import com.hobarb.internshalanotes.ui.fragments.AddUpdateNoteFragmentDirections
import com.hobarb.internshalanotes.ui.fragments.NotesFragment
import com.hobarb.internshalanotes.ui.fragments.NotesFragmentDirections
import com.hobarb.internshalanotes.utils.Constants

class NotesAdapter(val context: Context, allNotes: ArrayList<NotesModel>) :RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    var allNotes: ArrayList<NotesModel>
    init {
        this.allNotes = allNotes
    }

    inner class NoteViewHolder(itemview: View):RecyclerView.ViewHolder(itemview)
    {
        val binding = ItemNoteBinding.bind(itemview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {

            with(holder) {
                binding.tvNoteTitleItemNote.text = allNotes[position].note_title
                binding.tvNoteDescriptionItemNote.text = allNotes[position].note_description
                binding.cvParentItemNote.setOnClickListener {
                    val action = NotesFragmentDirections.actionNotesFragmentToAddUpdateNoteFragment(Constants.alertDialogUpdateNote,allNotes[position].note_id, allNotes[position].note_title, allNotes[position].note_description)
                    Navigation.findNavController(it).navigate(action)
                }

        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
}