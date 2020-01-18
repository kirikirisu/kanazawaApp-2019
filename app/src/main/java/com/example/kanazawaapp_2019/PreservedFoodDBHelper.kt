package com.example.kanazawaapp_2019

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

internal class PreservedFoodDBHelper(content: Context, databaseName: String, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(content, databaseName, factory, version) {

    override fun onCreate(database: SQLiteDatabase?) {
        database?.execSQL("create table if not exists PreservedFoodTable (" +
            "preserved_food_id integer primary key autoincrement, " +
            "food_name text," +
            "deadline text," +
            "storage_location text," +
            "quantity integer," +
            "created_at text not null default (datetime('now', 'localtime'))," +
            "commercial text);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL("drop table if exists PreservedFoodTable;")
        onCreate(db)
    }
}