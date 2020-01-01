package com.example.kanazawaapp_2019

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class popupNotification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_notification_setting)
    }
    //どのタイミングでポップアップを表示するか考える余地あり
    fun onTapEvent(view: a?) { //ここはまだ仮段階 以下も同様
        val waterPop = Toast.makeText(
            applicationContext,
            "飲料水が n L不足しています。",
            Toast.LENGTH_SHORT
        )
        waterPop.show()
    }

    fun onTapEvent(view: b?) {
        val stapleFoodPop = Toast.makeText(
            applicationContext,
            "主食が n 食不足しています。",
            Toast.LENGTH_SHORT
        )
        stapleFoodPop.show()
    }

    fun onTapEvent(view: c?) {
        val snackPop = Toast.makeText(
            applicationContext,
            "間食が n 個不足しています。",
            Toast.LENGTH_SHORT
        )
        snackPop.show()
    }
}