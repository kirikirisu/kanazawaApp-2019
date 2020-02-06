package com.example.kanazawaapp_2019.Model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.media.audiofx.PresetReverb
import android.provider.BaseColumns
import android.util.Log
import java.lang.Exception

class PreservedFoodReaderDbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    //insertFoodTypeにデモデータをinsertする
    fun insertFoodType() {
        val typeNames = arrayOf("飲み物", "食べ物", "スナック")
        val db = writableDatabase
        for (typeName in typeNames) {
            val values = ContentValues().apply {
                put(PreservedFoodReaderContract.PreservedFoodEntry.NAME, typeName)
            }
            try {
                val newRowId = db?.insert(PreservedFoodReaderContract.FoodTypeEntry.TABLE_NAME, null, values)
                Log.d("newRowId:", newRowId.toString())
            } catch (e: Exception) {
                Log.d("error", e.toString())
            }
        }
    }

    //Foodテーブルにinsertデモデータをinsertする。
    fun insertFood(preservedFoods: Array<PreservedFood>) {
        val db = writableDatabase
        for (preservedFood in preservedFoods) {
            val values = ContentValues().apply {
                put(PreservedFoodReaderContract.PreservedFoodEntry.NAME, preservedFood.name)
                put(PreservedFoodReaderContract.PreservedFoodEntry.QUANTITY, preservedFood.quantity)
                put(PreservedFoodReaderContract.PreservedFoodEntry.INITIAL_QUANTITY, preservedFood.initialQuantity)
                put(PreservedFoodReaderContract.PreservedFoodEntry.TYPE_ID, preservedFood.typeId)
                put(PreservedFoodReaderContract.PreservedFoodEntry.LOCATION, preservedFood.location)
                put(PreservedFoodReaderContract.PreservedFoodEntry.IS_HANDMAID, preservedFood.isHandMade)
                put(PreservedFoodReaderContract.PreservedFoodEntry.DEADLINE, preservedFood.deadline)
                put(PreservedFoodReaderContract.PreservedFoodEntry.IMAGE_PATH, preservedFood.imagePath)
            }
            try {
                val newRowId = db?.insert(PreservedFoodReaderContract.PreservedFoodEntry.TABLE_NAME, null, values)
                Log.d("newRowId:", newRowId.toString())
            } catch (e: Exception) {
                Log.d("error", e.toString())
            }
        }
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0!!.execSQL(SQL_CREATE_FOOD_TYPE)
        p0!!.execSQL(SQL_CREATE_PRESERVED_FOOD)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL(SQL_DELETE_PRESERVED_FOOD)
        p0!!.execSQL(SQL_DELETE_FOOD_TYPE)
        onCreate(p0)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }


    companion object {
        const val DATABASE_NAME = "StockSupporterDB"
        const val DATABASE_VERSION = 1
        //PreservedFoodテーブル作成のSQL
        private const val SQL_CREATE_PRESERVED_FOOD =
            "CREATE TABLE ${PreservedFoodReaderContract.PreservedFoodEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.NAME} TEXT NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.DEADLINE} TEXT NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.LOCATION} TEXT NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.QUANTITY} TEXT NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.TYPE_ID} INTEGER NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.INITIAL_QUANTITY} INTEGER NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.IS_HANDMAID} BOOLEAN NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.IMAGE_PATH} TEXT NOT NULL," +
                    "${PreservedFoodReaderContract.PreservedFoodEntry.CREATED_AT} TEXT NOT NULL DEFAULT (DATETIME('now', 'localtime'))," +
                    "FOREIGN KEY(${PreservedFoodReaderContract.PreservedFoodEntry.TYPE_ID}) REFERENCES FoodType(id))"
        //FoodTypeテーブル作成のSQL
        private const val SQL_CREATE_FOOD_TYPE =
            "CREATE TABLE ${PreservedFoodReaderContract.FoodTypeEntry.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${PreservedFoodReaderContract.FoodTypeEntry.NAME} TEXT NOT NULL)"
        //PreservedFoodテーブル削除のSQL
        private const val SQL_DELETE_PRESERVED_FOOD =
            "DROP TABLE IF EXISTS ${PreservedFoodReaderContract.PreservedFoodEntry.TABLE_NAME}"
        //FoodTypeテーブル削除のSQL
        private const val SQL_DELETE_FOOD_TYPE =
            "DROP TABLE IF EXISTS ${PreservedFoodReaderContract.FoodTypeEntry.TABLE_NAME}"

    }
}