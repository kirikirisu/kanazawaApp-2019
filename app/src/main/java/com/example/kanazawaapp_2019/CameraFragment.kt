package com.example.kanazawaapp_2019


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView

/**
 * A simple [Fragment] subclass.
 */
class CameraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_camera, container, false)

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
        }
        CameraRollButton.setOnClickListener{
            Log.d("追加","カメラロール")
        }
    }
    //バーコードボタンの挙動
    fun clickBarcode(){

    }
}
