package com.example.kanazawaapp_2019

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
// import androidx.core.content.getSystemService
import android.provider.Settings
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.IOException
import java.util.jar.Manifest


class ShelterActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {

    private lateinit var gMap: GoogleMap
    var column: Array<String> = emptyArray()
    var shelters: Array<ShelterData> = emptyArray()

    var currentLatitude: Double=0.toDouble()
    var currentLongitude: Double=0.toDouble()

    lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fetchCsv("shisetsu_hinan.csv")

        /*パーミッション確認*/
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1000)
        }else{
            locationStart()

            if(::locationManager.isInitialized){
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    15f,
                    this
                )
            }
        }
    }

    /* 現在地取得処理 */
    fun locationStart() {

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Log.d("debug", "location manager Enabled")
        } else {
            // to prompt setting up GPS
            val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(settingsIntent)
            Log.d("debug", "not gpsEnable, startActivity")
        }

        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1000)

            Log.d("debug", "checkSelfPermission false")
            return
        }

        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000,
            50f,
            this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1000) {
            // 使用が許可された
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("debug", "checkSelfPermission true")
                gMap.isMyLocationEnabled = true
                gMap.uiSettings.isMyLocationButtonEnabled = true
                locationStart()

            } else {
                // それでも拒否された時の対応
                val toast = Toast.makeText(this,
                    "これ以上なにもできません", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        /* API 29以降非推奨
        when (status) {
            LocationProvider.AVAILABLE ->
                Log.d("debug", "LocationProvider.AVAILABLE")
            LocationProvider.OUT_OF_SERVICE ->
                Log.d("debug", "LocationProvider.OUT_OF_SERVICE")
            LocationProvider.TEMPORARILY_UNAVAILABLE ->
                Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE")
        }
         */
    }

    override fun onLocationChanged(location: Location) {
        currentLatitude = location.latitude
        currentLongitude = location.longitude
        Log.d("debug", "${currentLongitude}")
        Log.d("debug", "${currentLatitude}")
    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

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

    fun setUpMap() {
        gMap.isMyLocationEnabled = true
        gMap.uiSettings.isMyLocationButtonEnabled = true
    }

    /* mapの処理 */
    override fun onMapReady(googleMap: GoogleMap) {
        gMap = googleMap
        mappingMarker(shelters)

        gMap.uiSettings.isZoomControlsEnabled = true

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                // ActivityCompat.requestPermissions(this,
                    // arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1000)
                return
            }else{
                setUpMap()
            }
        }

        val kanazawa = LatLng(36.561031, 136.656647)
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kanazawa, 16f))
    }
}
