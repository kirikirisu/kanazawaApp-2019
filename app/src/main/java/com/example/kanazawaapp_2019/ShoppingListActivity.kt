package com.example.kanazawaapp_2019

import android.app.LauncherActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_shoppinglist.*

class ShoppingListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppinglist)

        //保存食追加ボタンの実装
        shoppingListAddButton.setOnClickListener {
            val intent = Intent(application, FoodAdditionActivity::class.java)
            startActivity(intent)
        }

        //listにテストデータを追加
        val listView = findViewById(R.id.shoppingListView) as ListView
        val dataArray = arrayOf("カロリーメイト", "鯖缶")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
        listView.adapter = adapter

        //ショッピングサイトへ遷移するのフラグメントを呼び出す
        shoppingListView.setOnItemClickListener { _: AdapterView<*>, _: View, _: Int, _: Long ->
            val fragment = shoppingSiteFragment()
            val fragmentManager = this.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }
    }
}