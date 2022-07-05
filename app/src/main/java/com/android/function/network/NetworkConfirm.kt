package com.android.function.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.android.function.R

class NetworkConfirm : AppCompatActivity() {
// <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    var confirm_result :Boolean? = null
    var get_network :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_confirm)

        get_network = findViewById(R.id.get_network)

        val confirm_network : Button = findViewById(R.id.confirm_network)
        confirm_network.setOnClickListener(View.OnClickListener {
            confirmNetwork2()
        })

    }

    // Callback 활용 X
    // 실시간 확인 불가
    fun confirmNetwork(){
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (networkCapabilities != null){
                confirm_result = networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
            }
        } else {    // 29버전 이전
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null){
                confirm_result = (networkInfo.type == ConnectivityManager.TYPE_WIFI) || (networkInfo.type == ConnectivityManager.TYPE_MOBILE)
            }
        }
    }

    // 이 Callback을 등록하여 연결, 미연결을 Callback 받음
    val networkCallBack = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            // 네트워크가 연결될 때 호출
            confirm_result = true
            runOnUiThread(Runnable {
                get_network?.setText(confirm_result.toString())
            })
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            // 네트워크가 끊길 때 호출
            confirm_result = false
            runOnUiThread(Runnable {
                get_network?.setText(confirm_result.toString())
            })
        }
    }

    // Callback 활용 O
    // 실시간 확인 가능 (29버전 이전은 실시간 불가)
    fun confirmNetwork2(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val connectivityManager = getSystemService(ConnectivityManager::class.java)
            val networkRequest = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build()
            connectivityManager.registerNetworkCallback(networkRequest,networkCallBack)
        } else {
            // 29버전 이전은 실시간 확인 불가.
        }

    }


}