package com.hobarb.internshalanotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hobarb.internshalanotes.models.NotesModel
import com.hobarb.internshalanotes.R
import com.hobarb.internshalanotes.databinding.ItemNoteBinding

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
                binding.tvNoteTitleItNote.text = allNotes[position].note_title
                binding.tvNoteDescriptionItNote.text = allNotes[position].note_description

        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
}