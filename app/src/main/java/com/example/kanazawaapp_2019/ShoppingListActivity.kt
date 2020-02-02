package com.example.kanazawaapp_2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
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

        //footerの実装
        shoppingListTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var position = shoppingListTabLayout.selectedTabPosition
                when (position) {
                    0 -> {
                        val intent = Intent(application, PreservedFoodListActivity::class.java)
                        startActivities(arrayOf(intent))
                    }
                    else -> {
                        shoppingListView.setSelection(0)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {
                var position = shoppingListTabLayout.selectedTabPosition
                when (position) {
                    0 -> {val intent = Intent(application, PreservedFoodListActivity::class.java)
                    startActivities(arrayOf(intent))
                    }
                    else -> {
                    shoppingListView.setSelection(0)
                    }
                }
            }
        })

    }
}