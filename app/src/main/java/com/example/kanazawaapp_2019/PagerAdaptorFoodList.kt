package com.example.kanazawaapp_2019

import DrinkFragmentActivity
import SnackFragmentActivity
import StapleFoodFragmentActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout

val tabsFoodList = 3
// number of tabs

class PagerAdapterFoodList(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                DrinkFragmentActivity()
            }

            1 -> {
                StapleFoodFragmentActivity()
            }

            else -> {
                return SnackFragmentActivity()
            }
            //this method is set our tabs positions
        }
    }

    override fun getCount(): Int {
        return tabsFoodList
        // this method will return 2 tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "飲料水"
            1 -> "主食"
            else -> {
                return "間食"
            }
            // this method will set our tabs titles
        }
    }
}
