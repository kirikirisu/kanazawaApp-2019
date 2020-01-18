package com.example.kanazawaapp_2019

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception


class SqlSampleActivity: AppCompatActivity() {
    private val dbName: String = "StockSupporterDB"
    private val tableName: String = "PreservedFoodTable"
    private val dbVersion: Int = 1
    private val arrayListPreservedFoodId: ArrayList<Int> = arrayListOf()
    private val arrayListFoodName: ArrayList<String> = arrayListOf()
    private val arrayListDeadline: ArrayList<Int> = arrayListOf()
    private val arrayListStorageLocation: ArrayList<String> = arrayListOf()
    private val arrayListQuantity: ArrayList<Int> = arrayListOf()
    private val arrayListCreatedAt: ArrayList<String> = arrayListOf()
    private val arrayListCommercial: ArrayList<String> = arrayListOf()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_sample)
        insertData(1, "カロリーメイト", "2010-2-15", "棚", 4, "2020-1-18",true)
        selectData()
    }

    private fun insertData(preserved_food_id: Int, food_name: String, deadline: String, storage_location: String, quantity: Int, created_at: String, commercial: Boolean){
        try{
            val dbHelper = PreservedFoodDBHelper(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.writableDatabase

            val values = ContentValues()
            values.put("preserved_food_id", 1)
            values.put("food_name", "カロリーメイト")
            values.put("deadline", "2020-2-15")
            values.put("storage_location", "棚")
            values.put("quantity", 4)
            values.put("created_at", "2020-1-18")
            values.put("commercial", "true")
            database.insertOrThrow(tableName, null, values)
        }catch(exception: Exception){
            Log.e("Log", exception.toString())
        }
    }

    private fun selectData() {
        try{
            arrayListPreservedFoodId.clear()
            arrayListFoodName.clear()
            arrayListDeadline.clear()
            arrayListStorageLocation.clear()
            arrayListQuantity.clear()
            arrayListCreatedAt.clear()
            arrayListCommercial.clear()


            val dbHelper = PreservedFoodDBHelper(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select * from PreservedFoodTable"
            val cursor = database.rawQuery(sql, null)
            if(cursor.count > 0){
                cursor.moveToFirst()
                while (!cursor.isAfterLast){
                    arrayListPreservedFoodId.add(cursor.getInt(0))
                    arrayListFoodName.add(cursor.getString(1))
                    arrayListDeadline.add(cursor.getInt(2))
                    arrayListStorageLocation.add(cursor.getString(3))
                    arrayListCreatedAt.add(cursor.getString(4))
                    arrayListQuantity.add(cursor.getInt(5))
                    arrayListCommercial.add(cursor.getString(6))
                    cursor.moveToNext()
                }
            }
            Log.d("INFO", arrayListFoodName.toString())
        }catch(exeption: Exception) {
            Log.e("Log", exeption.toString())
        }
    }

}
