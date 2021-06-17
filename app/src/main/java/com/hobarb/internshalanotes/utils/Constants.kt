package com.hobarb.internshalanotes.utils

class Constants {
    companion object{

        const val database_name:String = "InternshalaNotes"
        const val table_name:String = "MyNotes"
        const val colNameNoteId:String = "note_id"
        const val colNameNoteTitle:String = "note_title"
        const val colNameNoteDescription:String = "note_description"
        const val colNameNoteDatetime:String = "note_datetime"

        const val alertDialogAddNote:Int = 1001
        const val alertDialogUpdateNote:Int = 1002

    }
}