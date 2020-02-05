package com.example.kanazawaapp_2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat

class HowToLookFirstScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_look_first_screen)

        setUpToolbar()
    }

    fun setUpToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessError("toolbar cannot be null")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
