package com.example.kanazawaapp_2019

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sql_sample.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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

    private var editFoodName: String = ""
    private var editDeadline: String = ""
    private var editStorageLocation: String = ""
    private var editQuantity: Int = 0
    private var isCommercial: String = ""
    private var createdAt = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_sample)

        add_button.setOnClickListener {
            if(edit_food_name.text != null && edit_deadline != null && edit_storage_location != null && editQuantity != null) {
                editFoodName = edit_food_name.text.toString()
                editDeadline = edit_deadline.text.toString()
                editStorageLocation = edit_storage_location.text.toString()
                isCommercial = commercial_or_not.isChecked().toString()

                Log.d("デバックifの中", isCommercial.toString())
                if(edit_quantity.text.toString() != "" ){
                    editQuantity = edit_quantity.text.toString().toInt()
                }
            }
            getCreatedAt()

            insertData( editFoodName, editDeadline, editStorageLocation, editQuantity, createdAt,isCommercial)
            selectData()
        }
    }

    private fun getCreatedAt(): String {
        val date = Date()
        val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
        createdAt = format.format(date)
        return ""
    }

    private fun insertData(editFoodName: String, editDeadline: String, editStorageLocation: String, editQuantity: Int, createdAt: String, isCommercial: String){
        try{
            val dbHelper = PreservedFoodDBHelper(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.writableDatabase


            val values = ContentValues()
            values.put("food_name", editFoodName)
            values.put("deadline", editDeadline)
            values.put("storage_location", editStorageLocation)
            values.put("quantity", editQuantity)
            values.put("created_at", createdAt)
            values.put("commercial", isCommercial)
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
            Log.d("デバック1", arrayListPreservedFoodId.toString())
            Log.d("デバック2", arrayListFoodName.toString())
            Log.d("デバック3", arrayListCommercial.toString())
        }catch(exeption: Exception) {
            Log.e("Log", exeption.toString())
        }
    }

}
