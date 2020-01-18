package com.example.kanazawaapp_2019

import android.app.LauncherActivity
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Camera
import android.os.Bundle
import android.os.PersistableBundle
import android.view.AbsSavedState
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_first_description.*
import kotlinx.android.synthetic.main.activity_food_addition.*
import java.nio.file.Files.delete
import java.util.*

class FoodAdditionActivity : AppCompatActivity(){

    //保存食のジャンル分け機能
    //選択肢
    private val spinnerItems= arrayOf("飲料水","主食","間食")

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_addition)

        //ImageButtonの処理
        photoView.setOnClickListener {
            val fragment = CameraFragment()
            val fragmentManager = this.supportFragmentManager
            val fragmentTransaction =fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit()
        }

        //ActionButtonの追加
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessError("toolbar cannot be, null")


        //ArrayAdapter
        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        //spinnerにadapterをセット
        //Kotlin Android Extension
        spinner.adapter=adapter

        //リスナーを設定
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String



            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        }

        //保存期限の選択機能
        //Calender
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        //button click to show DatePicker

        DatePicker.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    view ,mYear ,mMonth ,mDay ->
                //set TextView
                dateText.setText(""+mYear+"/"+mMonth+"/"+mDay)
            },year,month,day)
                //show dialog
            dpd.show()
        }

        //保存食量の選択ピッカー機能
        number_picker.minValue= 0
        number_picker.maxValue= 300

        number_picker.wrapSelectorWheel = true

    }

    //戻るボタンの処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
