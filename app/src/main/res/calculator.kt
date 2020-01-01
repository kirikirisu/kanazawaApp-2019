package com.example.kanazawaapp_2019

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_first_description.*
import kotlinx.android.synthetic.main.activity_initial_notification_setting.*

class calculater : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_number_setting)
        setContentView(R.layout.activity_initial_notification_setting)

        var humanNumber = intent.getIntExtra("humanNumber", 0)
        var savingDays = intent.getIntExtra("savingDays",0)
        //一人一日当たりの目安保存量×人数×日数 の計算を行うことで目安保存量を求める。
        var indicationWaterQuantity = 3 * humanNumber * savingDays
        var indicationStapleFoodQuantity = 3 * humanNumber * savingDays
        var indicationSnackQuantity = 1 * humanNumber * savingDays //間食の一人一日あたりの目安は考える余地あり(今回はとりあえず1とした)

        indicationStockQuantity.setText(indicationWaterQuantity.toString())
        indicationStockQuantity.setText(indicationStapleFoodQuantity.toString())
        indicationStockQuantity.setText(indicationSnackQuantity.toString())

        val Date = findViewById<TextView>(R.id.surviveDate)
        Date.text = humanNumber.toString()
    }


}