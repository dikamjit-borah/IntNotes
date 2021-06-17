package com.hobarb.internshalanotes.db_name

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import com.hobarb.internshalanotes.utils.Constants

class DbManager {
    //db name
    val db_name = Constants.database_name
    //table name
    val db_table = Constants.table_name

    var col_note_id = Constants.colNameNoteId
    var col_note_title = Constants.colNameNoteTitle
    var col_note_description = Constants.colNameNoteDescription

    var db_version = 1;

    val create_table_query = "CREATE TABLE IF NOT EXISTS ${db_table} (${col_note_id} INTEGER PRIMARY KEY, ${col_note_title} TEXT, ${col_note_description} TEXT)"
    val drop_table_query = "DROP TABLE IF EXISTS ${db_table} "


    var sqlite: SQLiteDatabase? = null
    
    constructor(context: Context)
    {
        var db = DatabaseHelper(context)
        sqlite = db.writableDatabase
    }

    inner class DatabaseHelper : SQLiteOpenHelper{

        var context:Context? = null
        constructor(context: Context):super(context, db_name, null, db_version){
            this.context = context
        }

        override fun onCreate(db_name: SQLiteDatabase?) {
            db_name!!.execSQL(create_table_query)

        }

        override fun onUpgrade(db_name: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
           db_name!!.execSQL(drop_table_query)
        }

    }

    fun insertIntoDb (values:ContentValues):Long{
        val id = sqlite!!.insert(db_table, "", values)
        return id;
    }

    fun queryFromDb (projection:Array<String>, selection:String, selectionArgs:Array<String>, sortOrder:String): Cursor? {

        val qb = SQLiteQueryBuilder()
        qb.tables = db_table
        val cursor = qb.query(sqlite, projection, selection, selectionArgs, null, null, sortOrder)

        return cursor;
    }

    fun deleteFromDb (selection: String, selectionArgs: Array<String>):Int {
        val count = sqlite!!.delete(db_table, selection, selectionArgs)
        return count
    }

    fun updateNote(values: ContentValues, selection: String, selectionArgs: Array<String>):Int{
        val count = sqlite!!.update(db_table, values, selection, selectionArgs)
        return count
    }
}
