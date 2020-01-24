package com.example.kanazawaapp_2019

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

val tabs = 2
// number of tabs

class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                NotificationFragment()
            }
            else -> {
                return NumberFragment()
            }
            //this method is set our tabs positions
        }
    }

    override fun getCount(): Int {
        return tabs
        // this method will return 2 tabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "通知"
            else -> {
                return "人数"
            }
            // this method will set our tabs titles
        }
    }
}
