package com.example.kanazawaapp_2019

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
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

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0 != null) {
                    when (p0.position){
                        0 ->{
                            tabLayout.setSelectedTabIndicatorColor(getColor(R.color.colorDrink))
                        }
                        1 ->{
                            tabLayout.setSelectedTabIndicatorColor(getColor(R.color.colorStapleFood))
                        }
                        2 -> {
                            tabLayout.setSelectedTabIndicatorColor(getColor(R.color.colorSnack))
                        }
                    }
                }
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }
        })
    }


}
