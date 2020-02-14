package com.example.kanazawaapp_2019

import android.app.DatePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_food_addition.*
import java.util.*

class FoodAdditionActivity : AppCompatActivity(){

    //保存食のジャンル分け機能
    //選択肢
    private val spinnerItems= arrayOf("飲料水","主食","間食")
    val PERMISSIN_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_addition)

        photoView.setImageResource(R.drawable.camera_icon)

        //ImageButtonの処理
        photoView.setOnClickListener {
            val fragment = CameraSelectionFragment()
            val fragmentManager = this.supportFragmentManager
            val fragmentTransaction =fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit()

            clickCameraButton()
        }

        //追加ボタンの処理
        additionButton.setOnClickListener {
            val intent = Intent(application, PreservedFoodListActivity::class.java)
            startActivity(intent)
        }

        //ActionBarの追加
        window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessError("toolbar cannot be, null")

        //ArrayAdapter
        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        //spinnerにadapterをセット
        //Kotlin Android Extension
        spinner.adapter=adapter

        //リスナーを設定
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        }

        //保存期限の選択機能
        //Calender
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        //button click to show DatePicker

        DatePicker.setOnClickListener{
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    view ,mYear ,mMonth ,mDay ->
                //set TextView
                dateText.setText(""+mYear+"/"+(mMonth+1)+"/"+mDay)
            },year,month,day)
                //show dialog
            dpd.show()
        }

        //保存食量の選択ピッカー機能
        number_picker.minValue= 0
        number_picker.maxValue= 300

        number_picker.wrapSelectorWheel = true

    }

    //カメラ認証
    fun clickCameraButton(){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            //なかった場合
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.CAMERA),PERMISSIN_CODE)
        }else{
            //あった場合
            val toast = Toast.makeText(this,
                "許可されました",Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
       if (requestCode == PERMISSIN_CODE){
           //許可がされた
           if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
               val toast = Toast.makeText(this,
                   "許可されました",Toast.LENGTH_SHORT)
               toast.show()
               Log.d("debug","checkSelfPermission true")
           }else{
               //拒否された場合
               val toast = Toast.makeText(this,
                   "何もできません",Toast.LENGTH_SHORT)
               toast.show()
           }
       }
    }

    //戻るボタンの処理
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
