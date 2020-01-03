package com.example.kanazawaapp_2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_shoppinglist.*

class ShoppingListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppinglist)

        //保存食追加ボタンの実装
        //shoppingListAddButton.setOnClickListener {
            //val intent = Intent(application, FoodAdditionActivity::class.java)
            //startActivity(intent)
        //}

        //listにテストデータを追加
        val listView = findViewById(R.id.shoppingListView) as ListView
        val dataArray = arrayOf("カロリーメイト", "鯖缶")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
        listView.adapter = adapter

    }
}