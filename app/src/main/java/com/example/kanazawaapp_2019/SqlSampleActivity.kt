package com.example.kanazawaapp_2019

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kanazawaapp_2019.DB.PreservedFoodDBHelper
import kotlinx.android.synthetic.main.activity_sql_sample.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class SqlSampleActivity: AppCompatActivity() {
    private var editFoodName: String = ""
    private var editDeadline: String = ""
    private var editStorageLocation: String = ""
    private var editQuantity: Int = 0
    private var createdAt = ""
    private var isCommercial: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sql_sample)

        add_button.setOnClickListener {
            Log.d("pushed", "押した")
            if(edit_food_name.text != null && edit_deadline != null && edit_storage_location != null && editQuantity != null) {
                editFoodName = edit_food_name.text.toString()
                editDeadline = edit_deadline.text.toString()
                editStorageLocation = edit_storage_location.text.toString()
                isCommercial = commercial_or_not.isChecked().toString()

                if(edit_quantity.text.toString() != "" ){
                    Log.d("型", "${edit_quantity.text::class.java}")
                    editQuantity = edit_quantity.text.toString().toInt()
                }
            }
            getCreatedAt()

            insertData(editFoodName, editDeadline, editStorageLocation, editQuantity, createdAt, isCommercial)
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
            val dbHelper = PreservedFoodDBHelper(applicationContext)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("${DBContract.PreservedFoodEntry.FOOD_NAME}", editFoodName)
                put("${DBContract.PreservedFoodEntry.DEADLINE}", editDeadline)
                put("${DBContract.PreservedFoodEntry.STORAGE_LOCATION}", editStorageLocation)
                put("${DBContract.PreservedFoodEntry.QUANTITY}", editQuantity)
                put("${DBContract.PreservedFoodEntry.CREATED_AT}", createdAt)
                put("${DBContract.PreservedFoodEntry.COMMERICAL}", isCommercial)
            }

            db.insert(DBContract.PreservedFoodEntry.TABLE_NAME, null, values)
        }catch(exception: Exception){
            Log.e("Log", exception.toString())
        }
    }

    private fun selectData() {
        try{
            val PreservedFoodIds = mutableListOf<Long>()
            val FoodNames = mutableListOf<Long>()
            val Deadlines = mutableListOf<Long>()
            val StorageLocations = mutableListOf<Long>()
            val Quantities = mutableListOf<Long>()
            val arrayListCreatedAt = mutableListOf<Long>()
            val Commercials = mutableListOf<Long>()

            PreservedFoodIds.clear()
            FoodNames.clear()
            Deadlines.clear()
            StorageLocations.clear()
            Quantities.clear()
            arrayListCreatedAt.clear()
            Commercials.clear()

            val dbHelper = PreservedFoodDBHelper(applicationContext)
            val db = dbHelper.readableDatabase

            val sql = "select * from ${DBContract.PreservedFoodEntry.TABLE_NAME}"
            val cursor = db.rawQuery(sql, null)
            with(cursor) {
                if (count > 0) {
                    moveToFirst()
                    while (moveToNext()) {
                        val PreservedFoodId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
                        val FoodName = getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.FOOD_NAME))
                        val Deadline = getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.DEADLINE))
                        val StorageLocation = getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.STORAGE_LOCATION))
                        val Quantity = getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.QUANTITY))
                        val CreatedAt = getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.CREATED_AT))
                        val Commercial = getLong(getColumnIndexOrThrow(DBContract.PreservedFoodEntry.COMMERICAL))

                        PreservedFoodIds.add(PreservedFoodId)
                        FoodNames.add(FoodName)
                        Deadlines.add(Deadline)
                        StorageLocations.add(StorageLocation)
                        Quantities.add(Quantity)
                        arrayListCreatedAt.add(CreatedAt)
                        Commercials.add(Commercial)
                    }
                }
            }
        }catch(exeption: Exception) {
            Log.e("Log", exeption.toString())
        }
    }
}
