package com.android.function.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.android.function.MainActivity
import com.android.function.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val delayTime:Long = 3000

        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        },delayTime)

    }
}