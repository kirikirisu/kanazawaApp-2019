package com.example.kanazawaapp_2019

import android.content.Intent
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class GettingStartMainActivity: AppCompatActivity() {

    lateinit var finalyIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_getting_start_main)

        setUpToolbar()

        renderList()
    }

   fun setUpToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessError("toolbar cannot be null")
    }

    fun renderList() {
        val listView = findViewById(R.id.listView) as ListView
        val dataArray = arrayOf("保存食追加の仕方", "リストの見方", "一覧の見方", "通知設定の仕方", "避難所の見方")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            // Log.i("debug", position.toString())
            when (position) {
                0 -> {
                    Log.i("debug", "保存食追加の仕方")
                    finalyIntent = Intent(this, HowToAddFoodActivity::class.java)
                }
                1 -> {
                    Log.i("debug", "リストの見方")
                    finalyIntent = Intent(this, HowToLookListActivity::class.java)
                }
                2 -> {
                    Log.i("debug", "一覧の見方")
                    finalyIntent = Intent(this, HowToLookFirstScreenActivity::class.java)
                }
                3 -> {
                    finalyIntent = Intent(this, HowToNotificationSettingsActivity::class.java)
                }
                4 -> {
                    finalyIntent = Intent(this, HowToLookShelterActivity::class.java)
                }
            }
            startActivity(finalyIntent)
        }
    }
}