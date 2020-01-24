package com.example.kanazawaapp_2019

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_preserved_foods_list.*

class PreservedFoodListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preserved_foods_list)

        setSupportActionBar(toolBar)
        // set adapter
        val fragmentAdapter = PagerAdapterFoodList(supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        // tablayout with viewpager
        tabLayout.setupWithViewPager(viewPager)
    }
}
