package com.example.kanazawaapp_2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shoppinglist.*

class ShoppingListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppinglist)

        //保存食追加ボタンの実装
        shoppingListAddButton.setOnClickListener {
//            val intent = Intent(application, FoodAdditionActivity::class.java)
            startActivity(intent)
        }

    }
}