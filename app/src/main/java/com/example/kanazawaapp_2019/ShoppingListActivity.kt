package com.example.kanazawaapp_2019

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_shoppinglist.*

interface FragmentCallInterface {
    fun setFragment()
}

class ShoppingListActivity : AppCompatActivity(), FragmentCallInterface{

    var ListView: ListView? = null

    override fun setFragment() {
        val fragment = ShoppingSiteFragment()
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoppinglist)

        //保存食追加ボタンの実装
        shoppingListAddButton.setOnClickListener {
//            val intent = Intent(application, FoodAdditionActivity::class.java)
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
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
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
                    0 -> {
                        val intent = Intent(application, PreservedFoodListActivity::class.java)
                        startActivities(arrayOf(intent))
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
                    }
                    else -> {
                    shoppingListView.setSelection(0)
                    }
                }
            }
        })
        //listにテストデータを追加
        ListView = findViewById(R.id.shoppingListView)

        var adapter = ShoppingListAdapter(this,generateData(), listener = this)
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