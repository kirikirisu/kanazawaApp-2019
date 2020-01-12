package com.example.kanazawaapp_2019

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_description)
    }

    //終了時のアニメーションを定義
    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}
