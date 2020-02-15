package com.example.kanazawaapp_2019

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ToggleButton
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_preserved_foods_list.*

class PreservedFoodListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preserved_foods_list)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }?:IllegalAccessError("toolbar cannot be,null")

        val actionBarDrawerToggle = ActionBarDrawerToggle(this, drawer_layout,toolBar,R.string.app_name, R.string.app_name)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


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

        //保存食追加ボタンの実装
        shoppingListAddButton.setOnClickListener {
            val intent = Intent(application, FoodAdditionActivity::class.java)
            startActivity(intent)
        }

        //買い物リストへ
        shoppingListTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                var position = shoppingListTabLayout.selectedTabPosition
                when (position) {
                    1 -> {
                        val intent = Intent(application, ShoppingListActivity::class.java)
                        startActivities(arrayOf(intent))
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {
                var position = shoppingListTabLayout.selectedTabPosition
                when (position) {
                    1 -> {val intent = Intent(application, ShoppingListActivity::class.java)
                        startActivities(arrayOf(intent))
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    }
                }
            }
        })
    }


}
