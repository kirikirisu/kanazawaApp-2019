package com.example.kanazawaapp_2019.DB

import android.provider.BaseColumns
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

internal class PreservedFoodDBHelper(content: Context) :
    SQLiteOpenHelper(content, DATABASE_NAME, null, DATABASE_VERSION) {

    private val SQL_CREATE_ENTRIES =
        "create table if not exists ${DBContract.PreservedFoodEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} integer primary key autoincrement, " +
                "${DBContract.PreservedFoodEntry.FOOD_NAME} text," +
                "${DBContract.PreservedFoodEntry.DEADLINE} text," +
                "${DBContract.PreservedFoodEntry.STORAGE_LOCATION} text," +
                "${DBContract.PreservedFoodEntry.QUANTITY} integer," +
                "${DBContract.PreservedFoodEntry.CREATED_AT} text not null default (datetime('now', 'localtime'))," +
                "${DBContract.PreservedFoodEntry.COMMERICAL} text);"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${DBContract.PreservedFoodEntry.TABLE_NAME}"

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL(SQL_CREATE_ENTRIES)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        private val DATABASE_NAME: String = "Food.db"
        const val DATABASE_VERSION = 1
    }
}
