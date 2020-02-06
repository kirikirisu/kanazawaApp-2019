package com.example.kanazawaapp_2019

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_initial_notification_setting.*

class InitialNotificationSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_notification_setting)

        //前の画面より人数を取得
        val humanNumber = intent.getIntExtra("humanNumber",0)
        val strHumanNumber = humanNumber.toString()
        val numHumanNumber = strHumanNumber.toInt()

        surviveDate.text = "日分(" + strHumanNumber + "人分)"

            savingDays.addTextChangedListener(object : TextWatcher {
                //ユーザーの入力した値に応じ、即時に対応する値を表示させるプログラム
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    var trueFalse = true
                    val savingDaysText = findViewById<EditText>(R.id.savingDays)
                    val daysNumber = savingDaysText.text.toString()

                    if (daysNumber.isEmpty()) {
                        trueFalse = false
                        indicationWaterQuantity.text = ""
                        indicationStapleFoodQuantity.text = ""
                        indicationSnackQuantity.text = ""
                    }

                    if (trueFalse) {
                        val surviveDays = findViewById(R.id.savingDays) as EditText
                        val strSurviveDays = surviveDays.text.toString()
                        val numSurviveDays = strSurviveDays.toInt()

                        val numIndicationWaterQuantity = numHumanNumber * numSurviveDays * 3
                        val numIndicationStapleFoodQuantity = numHumanNumber * numSurviveDays * 3
                        val numIndicationSnackQuantity = numHumanNumber * numSurviveDays * 1 //この計算式(間食の目安)は仮

                        val indicationWaterQuantity = findViewById(R.id.indicationWaterQuantity) as TextView
                        val indicationStapleFoodQuantity = findViewById(R.id.indicationStapleFoodQuantity) as TextView
                        val indicationSnackQuantity = findViewById(R.id.indicationSnackQuantity) as TextView

                        val strIndicationWaterQuantity = numIndicationWaterQuantity.toString()
                        val strIndicationStapleFoodQuantity = numIndicationStapleFoodQuantity.toString()
                        val strIndicationSnackQuantity = numIndicationSnackQuantity.toString()
                        indicationWaterQuantity.text = strIndicationWaterQuantity
                        indicationStapleFoodQuantity.text = strIndicationStapleFoodQuantity
                        indicationSnackQuantity.text = strIndicationSnackQuantity
                    }
                }
            })

        val nextButton = findViewById<Button>(R.id.moveNextBtn)

        nextButton.setOnClickListener{
            var isValid = true

            val savingDaysText = findViewById<EditText>(R.id.savingDays)
            val DaysNumber = savingDaysText.text.toString()

            if (DaysNumber.isEmpty()) {
                savingDaysText.error = "入力してください"
                isValid = false
            }

            val notificationDateText = findViewById<EditText>(R.id.notificationDate)
            val number = notificationDateText.text.toString()

            if (number.isEmpty()) {
                notificationDateText.error = "入力してください"
                isValid = false
            }

            if (isValid) {
                val notificationDate = Integer.parseInt(number)

                val intent = Intent(this, PreservedFoodListActivity::class.java)
                intent.putExtra("notificationDate",notificationDate)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}