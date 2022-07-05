package com.android.function

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.android.function.accessRight.AccessRightActivity
import com.android.function.getLocation.LocationActivity
import com.android.function.mvvm.MvvmActivity
import com.android.function.network.NetworkConfirm
import com.android.function.splash.SplashActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var go_mvvm:Button? = null
    var go_splash:Button? = null
    var go_access_right:Button? = null
    var go_location:Button? = null
    var go_network:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        go_mvvm = findViewById(R.id.go_mvvm)
        go_mvvm!!.setOnClickListener(this)

        go_splash = findViewById(R.id.go_splash)
        go_splash!!.setOnClickListener(this)

        go_access_right = findViewById(R.id.go_access_right)
        go_access_right!!.setOnClickListener(this)

        go_location = findViewById(R.id.go_location)
        go_location!!.setOnClickListener(this)

        go_network = findViewById(R.id.go_network)
        go_network!!.setOnClickListener(this)

    }

        override fun onClick(v: View?) {
            when(v?.id){
                R.id.go_mvvm -> {   // MVVM 디자인 패턴
                    goActivity(MvvmActivity())
                }
                R.id.go_splash -> { // Splash 화면
                    goActivity(SplashActivity())
                }
                R.id.go_access_right -> {   // access 확인
                    goActivity(AccessRightActivity())
                }
                R.id.go_location -> {
                    goActivity(LocationActivity())
                }
                R.id.go_network -> {
                    goActivity(NetworkConfirm())
                }

            }
        }

    fun goActivity(where : Activity){
        val intent = Intent(this,where::class.java)
        startActivity(intent)
    }


}