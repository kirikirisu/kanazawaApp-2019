package com.example.kanazawaapp_2019

import android.app.LauncherActivity
import android.content.Context
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
import android.widget.CheckBox
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_shoppinglist.*

class ShoppingListActivity : AppCompatActivity() {

    var ListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppinglist)

        //保存食追加ボタンの実装
        shoppingListAddButton.setOnClickListener {
//            val intent = Intent(application, FoodAdditionActivity::class.java)
            startActivity(intent)
        }

        //listにテストデータを追加
        ListView = findViewById(R.id.shoppingListView)
        // 呼び出したいメソッド
        fun setFragment() {
            val fragment = shoppingSiteFragment()
            val fragmentManager = this.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        var adapter = ShoppingListAdapter(this, generateData())
        ListView?.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun generateData(): ArrayList<ShoppingItem> {
        var result = ArrayList<ShoppingItem>()
        var part1 = ShoppingItem("カロリーメイト","10個")
        result.add(part1)
        var part2 = ShoppingItem("鯖缶","20個")
        result.add(part2)
        return result
    }
}