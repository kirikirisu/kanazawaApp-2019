package com.example.kanazawaapp_2019

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException




class ShelterActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var gMap: GoogleMap
    var column: Array<String> = emptyArray()
    var shelters: Array<ShelterData> = emptyArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fetchCsv("shisetsu_hinan.csv")
    }

    /* csvの処理 */
    fun fetchCsv(fileName: String) {
        try{
            val file = resources.assets.open(fileName)
            val fileReader = BufferedReader(InputStreamReader(file))
            var i: Int = 0
            fileReader.forEachLine {
                if(it.isNotBlank()) {
                    if(i == 0) {
                        column = it.split(",").toTypedArray()
                    }else{
                        val line = it.split(",").toTypedArray()
                        shaping(line)
                    }
                }
                i++;
            }
        }catch (e: IOException) {
            //例外処理
            print(e)
        }
    }

    fun shaping(line: Array<String>){
        var name = line[10]
        var latitude = line[2]
        var longitude = line[3]


        val shelter = ShelterData(
            name = name,
            latitude = latitude,
            longitude = longitude
        )
        shelters +=  shelter
    }

    fun mappingMarker(shelters: Array<ShelterData>) {
        for(i in shelters) {
            val lat = i.latitude?.toDouble()
            val lon = i.longitude?.toDouble()
            val location = LatLng(lat!!, lon!!)
            gMap.addMarker(MarkerOptions().position(location))
        }

    }

    /* mapの処理 */
    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap

        // Add a marker in Sydney and move the camera
        val kanazawa = LatLng(36.561031, 136.656647)
        gMap.addMarker(MarkerOptions().position(kanazawa).title("kanazawa"))
        gMap.moveCamera(CameraUpdateFactory.newLatLng(kanazawa))
        mappingMarker(shelters)
    }
}
