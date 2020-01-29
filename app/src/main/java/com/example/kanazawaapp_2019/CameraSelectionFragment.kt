package com.example.kanazawaapp_2019


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.kittinunf.fuel.httpGet
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_food_addition.*
import java.io.BufferedInputStream
import com.github.kittinunf.result.Result;

//import sun.jvm.hotspot.utilities.IntArray
import java.io.IOException


/**
 * A simple [Fragment] subclass.
 */
class CameraSelectionFragment : Fragment() {
    val READRE_REQUEST_CODE : Int =42

    private lateinit var display :ImageView
    private lateinit var uri: Uri

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera_selection, container, false)
    }

    override fun onStart() {
        super.onStart()
        val BarcodeButton: Button = activity!!.findViewById(R.id.BarcodeReading)
        val CameraButton: Button = activity!!.findViewById(R.id.PictureTaking)
        val CameraRollButton : Button = activity!!.findViewById(R.id.PictureUsing)
        BarcodeButton.setOnClickListener{
            Log.d("追加","バーコード")
            clickBarcode()
        }
        CameraButton.setOnClickListener{
            Log.d("追加","カメラ")
            clickCamera()
        }
        CameraRollButton.setOnClickListener{
            Log.d("追加","カメラロール")
            clickCameraRoll()
        }
    }
    //バーコードボタンの挙動
    fun clickBarcode(){
        IntentIntegrator.forSupportFragment(this).initiateScan()
        var integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES)
        integrator.setPrompt("Scan a barcode")
        integrator.setCameraId(0)  // Use a specific camera of the device
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }

    //カメラボタンの挙動
    fun clickCamera(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ takePictureIntent ->
            takePictureIntent.resolveActivity(this.context!!.packageManager)?.also {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                    addCategory(Intent.CATEGORY_DEFAULT)
                }
                    startActivityForResult(intent, 1)
            }?:Toast.makeText(this.context,"カメラを扱うアプリがありません",Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        display = activity!!.displayPhoto
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        when {
            result != null -> {
                Log.d("バーコード",result.toString())

                val httpAsync = "https://shopping.yahooapis.jp/ShoppingWebService/V1/itemSearch?appid=dj00aiZpPUdwOFNBamp0S0FjeiZzPWNvbnN1bWVyc2VjcmV0Jng9MjU-&jan=${result.contents}"
                    .httpGet()
                    .responseString { request, response, result ->
                        when (result) {
                            is Result.Failure -> {
                                val ex = result.getException()
                                Log.d("バーコード",ex.toString())
                            }
                            is Result.Success -> {
                                val data = result.get()
                                Log.d("バーコード",data)
                            }
                        }
                    }
                httpAsync.join()
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            display.setImageBitmap(imageBitmap)
            Log.d("写真",imageBitmap.toString())

        }
        //カメラロールの画像を出力
        else if(requestCode == READRE_REQUEST_CODE){
            try {
                data?.data?.also { uri ->
                    val inputStream = this.context!!.contentResolver?.openInputStream(uri)
                    val buffered = BufferedInputStream(inputStream)
                    val opt = BitmapFactory.Options()
                    opt.inJustDecodeBounds = false
                    val image = BitmapFactory.decodeStream(buffered,null,opt)
                    inputStream!!.close()
                    display.setImageBitmap(image)
                    Log.d("写真",image.toString())
            }
            } catch (e: Exception) {
                Log.d("写真","失敗")
            }
        }
    }

    //カメラロールボタンの挙動
    fun clickCameraRoll(){
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }
        startActivityForResult(intent,READRE_REQUEST_CODE)
    }
}
