package com.android.function.getLocation

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.function.R

class LocationActivity : AppCompatActivity() {
//<uses-permission android:name="android.permission.INTERNET" />
//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
//<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />


    var txt_latitude : TextView? = null
    var txt_longitude : TextView? = null
    var txt_altitude : TextView? = null
    var txt_accuracy : TextView? = null

    var locationManager : LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        txt_latitude = findViewById(R.id.txt_latitude)
        txt_longitude = findViewById(R.id.txt_longitude)
        txt_altitude = findViewById(R.id.txt_altitude)
        txt_accuracy = findViewById(R.id.txt_accuracy)

        val get_location : Button = findViewById(R.id.get_location)
        get_location.setOnClickListener(View.OnClickListener {
            callLocation()
        })

    }

    // 위치가 변할 때 LocationManager로부터 notification
    val locationListener = object : LocationListener{
        override fun onLocationChanged(location: Location) {
            var latitude = location.latitude
            var longitude = location.longitude
            var altitude = location.altitude
            var accuracy = location.accuracy

            txt_latitude?.setText(latitude.toString())
            txt_longitude?.setText(longitude.toString())
            txt_altitude?.setText(altitude.toString())
            txt_accuracy?.setText(accuracy.toString())

            Log.d("LocationActivity","latitude : ${latitude} \n longitude : ${longitude} \n altitude : ${altitude} \n accuracy : ${accuracy}")

        }

    }

    @SuppressLint("MissingPermission")
    fun callLocation(){
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

        // 정해진 주기만큼 계속 얻어옴

        // GPS로 위치 얻는 방법
//        locationManager!!.requestLocationUpdates(
//            LocationManager.GPS_PROVIDER,3000L,100f,locationListener
//        )

        // Network로 위치 얻는 방법
        locationManager!!.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,3L,1f,locationListener
        )


    }

}