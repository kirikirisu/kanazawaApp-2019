package com.example.kanazawaapp_2019

import DrinkFragmentActivity
import SnackFragmentActivity
import StapleFoodFragmentActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.kanazawaapp_2019.R
import kotlinx.android.synthetic.main.activity_preserved_foods_list.*

class PreservedFoodsListActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preserved_foods_list)
        // buttonを参照
        val firstbutton = findViewById<Button>(R.id.stapleFoodButton)
        val secondbutton = findViewById<Button>(R.id.snackButton)
        val thirdbutton = findViewById<Button>(R.id.drinkButton)
        // FirstFragmentActivityクラスをインスタンス化その下も同様。
        val firstFragment = StapleFoodFragmentActivity()
        val secondFragment = SnackFragmentActivity()
        val thirdFragment = DrinkFragmentActivity()
        // buttonをクリックしたときにreplaceFragmentメソッドを実行
        firstbutton.setOnClickListener {
            replaceFragment(firstFragment)
        }

        secondbutton.setOnClickListener {
            replaceFragment(secondFragment)
        }

        thirdbutton.setOnClickListener {
            replaceFragment(thirdFragment)
        }
    }

//    R.id.containerに引数で渡されたフラグメントを入れる。
    fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.commit()
    }
}