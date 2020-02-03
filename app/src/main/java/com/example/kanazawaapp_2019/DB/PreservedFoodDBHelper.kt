package com.example.kanazawaapp_2019.DB

import android.content.ContentValues
import android.provider.BaseColumns
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

internal class PreservedFoodDBHelper(content: Context) :
    SQLiteOpenHelper(content, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        private val DATABASE_NAME: String = "Food.db"
        const val DATABASE_VERSION = 1
    }

    private val SQL_CREATE_ENTRIES =
        "create table if not exists ${DBContract.PreservedFoodEntry.TABLE_NAME} (" +
                "${BaseColumns._ID} integer primary key autoincrement, " +
                "${DBContract.PreservedFoodEntry.FOOD_NAME} text," +
                "${DBContract.PreservedFoodEntry.DEADLINE} text," +
                "${DBContract.PreservedFoodEntry.STORAGE_LOCATION} text," +
                "${DBContract.PreservedFoodEntry.QUANTITY} integer," +
                "${DBContract.PreservedFoodEntry.CREATED_AT} text not null default (datetime('now', 'localtime'))," +
                "${DBContract.PreservedFoodEntry.COMMERICAL} text);" +
                "${DBContract.PreservedFoodEntry.FOOD_TYPE} integer"

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

//    fun getCreatedAt(): String {
//        val date = Date()
//        val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
//        createdAt = format.format(date)
//        return ""
//    }

    fun insertPreservedFoods(foodName: String, deadline: String, storageLocation: String, quantity: Int, createdAt: String, commercial: String, foodType: Int){
        try{
            val db = this.writableDatabase
            val values = ContentValues().apply {
                put("${DBContract.PreservedFoodEntry.FOOD_NAME}", foodName)
                put("${DBContract.PreservedFoodEntry.DEADLINE}", deadline)
                put("${DBContract.PreservedFoodEntry.STORAGE_LOCATION}", storageLocation)
                put("${DBContract.PreservedFoodEntry.QUANTITY}", quantity)
                put("${DBContract.PreservedFoodEntry.CREATED_AT}", createdAt)
                put("${DBContract.PreservedFoodEntry.COMMERICAL}", commercial)
                put("${DBContract.PreservedFoodEntry.FOOD_TYPE}", foodType)
            }
            db.insert(DBContract.PreservedFoodEntry.TABLE_NAME, null, values)
            db.close()
        }catch(exception: Exception){
            Log.e("DB Insert Error", exception.toString())
        }
    }

//    private fun selectPreservedFoods(){
//        try{
//            val PreservedFoodIds = mutableListOf<Long>()
//            val FoodNames = mutableListOf<Long>()
//            val Deadlines = mutableListOf<Long>()
//            val StorageLocations = mutableListOf<Long>()
//            val Quantities = mutableListOf<Long>()
//            val arrayListCreatedAt = mutableListOf<Long>()
//            val Commercials = mutableListOf<Long>()
//
//            val db = this.readableDatabase
//            val sql = "select * from ${DBContract.PreservedFoodEntry.TABLE_NAME}"
//            val cursor = db.rawQuery(sql, null)
//
//            with(cursor) {
//                if (count > 0) {
//                    moveToFirst()
//                    while (moveToNext()) {
//                        val PreservedFoodId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
//                        val FoodName =
//                            getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.FOOD_NAME))
//                        val Deadline =
//                            getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.DEADLINE))
//                        val StorageLocation =
//                            getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.STORAGE_LOCATION))
//                        val Quantity =
//                            getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.QUANTITY))
//                        val CreatedAt =
//                            getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.CREATED_AT))
//                        val Commercial =
//                            getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.COMMERICAL))
//
//                        PreservedFoodIds.add(PreservedFoodId)
//                        FoodNames.add(FoodName)
//                        Deadlines.add(Deadline)
//                        StorageLocations.add(StorageLocation)
//                        Quantities.add(Quantity)
//                        arrayListCreatedAt.add(CreatedAt)
//                        Commercials.add(Commercial)
//                    }
//                }
//            }
//        }catch(exeption: Exception) {
//            Log.e("Log", exeption.toString())
//        }
//    }
}
