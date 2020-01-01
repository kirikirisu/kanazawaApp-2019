package com.example.kanazawaapp_2019

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class pushNotification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial_number_setting)
        setContentView(R.layout.activity_initial_notification_setting)

        var notificationDate = intent.getIntExtra("notificationDate",0)
        //この値と現在の日付、保存食の期限を利用することで適切なタイミングで表示する

        //現在の日時を習得
        fun getToday(): String {
            val date = Date()
            val format = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
            return format.format(date)
        }
        //参考 https://ameblo.jp/s1lva/entry-12381135777.html

        // 期限の日付 － 今日の日付 < ユーザーが入力した日付   この式を満たす時に通知
    }
}