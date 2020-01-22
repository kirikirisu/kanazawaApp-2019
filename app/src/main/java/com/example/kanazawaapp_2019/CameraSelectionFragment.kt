package com.example.kanazawaapp_2019


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class CameraSelectionFragment : Fragment() {
val READRE_REQUEST_CODE : Int =42

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

    }

    //カメラボタンの挙動
    fun clickCamera(){
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also{ takePictureIntent ->
            takePictureIntent.resolveActivity(this.context!!.packageManager)?.also {
                startActivityForResult(takePictureIntent,1)
            }?:Toast.makeText(this.context,"カメラを扱うアプリがありません",Toast.LENGTH_LONG).show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.RESULT_OK){
            Log.d("カメラロール","succsess")
        }
        when (requestCode){
            READRE_REQUEST_CODE -> {
                try {
                    data?.data?.also { uri ->
                        Log.d("カメラロール",uri.toString())
                    }
                }catch (e: Exception){

                }
            }
        }
    }
}
