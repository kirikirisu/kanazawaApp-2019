package com.example.kanazawaapp_2019

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class FirstDescriptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_description)
    }

    fun toNext(view: View) {
           val intent = Intent(this, SecondDescriptionActivity::class.java)
        startActivity(intent)
        //遷移するアニメーションを定義
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}
