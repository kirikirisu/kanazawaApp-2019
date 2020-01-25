package com.example.kanazawaapp_2019

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InitialNumberSettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_number_setting)

        val nextButton = findViewById<Button>(R.id.moveNextBtn)

        nextButton.setOnClickListener{
            var isValid = true

            val humanNumberText = findViewById<EditText>(R.id.humanNumber)
            val number = humanNumberText.text.toString()

            if (number.isEmpty()) {
                humanNumberText.error = "入力してください"
                isValid = false
            }

            if (isValid) {
                val humanNumber = Integer.parseInt(number)

                val intent = Intent(this, InitialNotificationSettingActivity::class.java)
                intent.putExtra("humanNumber",humanNumber)
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