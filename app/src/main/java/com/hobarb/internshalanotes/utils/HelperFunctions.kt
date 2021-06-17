package com.hobarb.internshalanotes.utils

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import com.hobarb.internshalanotes.R
import com.hobarb.internshalanotes.db_name.DbManager

class HelperFunctions {
    companion object{
        fun createAlertDialog(context: Context, alertCode:Int){
            val builder = AlertDialog.Builder(context)
            val customView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null)
            var noteTitleEt = customView.findViewById<EditText>(R.id.et_noteTitle_dialog)
            var noteDescriptionEt = customView.findViewById<EditText>(R.id.et_noteDescription_dialog)
            builder.setView(customView)
            when(alertCode){
                Constants.alertDialogAddNote -> {
                    builder.setPositiveButton("Add Note"){dialogInterface, which ->
                        val dbManager = DbManager(context)

                        var noteId = System.currentTimeMillis()
                        var noteTitle = noteTitleEt.text.toString()
                        var noteDescription = noteDescriptionEt.text.toString()

                        var values = ContentValues()
                        values.put(Constants.colNameNoteId, noteId)
                        values.put(Constants.colNameNoteTitle, noteTitle)
                        values.put(Constants.colNameNoteDescription, noteDescription)

                        var id = dbManager.insertIntoDb(values)

                        Toast.makeText(context,"${id} Note created",Toast.LENGTH_SHORT).show()
                    }

                }
                Constants.alertDialogUpdateNote -> {
                    builder.setPositiveButton("Update Note"){dialogInterface, which ->
                        Toast.makeText(context,"Note updated",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            builder.create().show()
        }
    }
}