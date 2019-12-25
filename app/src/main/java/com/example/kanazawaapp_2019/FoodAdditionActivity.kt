package com.example.kanazawaapp_2019

import android.os.Bundle
import android.os.PersistableBundle
import android.view.AbsSavedState
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first_description.*
import kotlinx.android.synthetic.main.activity_food_addition.*

class FoodAdditionActivity : AppCompatActivity(){

    //選択肢
    private val spinnerItems= arrayOf("飲料水","主食","間食")

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_addition)

        //val spinner = findViewById<Spinner>(R.id.spinner)

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


                //Kotlin Android Extension
                textView.text = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        }
    }
}