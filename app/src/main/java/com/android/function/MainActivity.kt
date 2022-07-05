package com.android.function

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.android.function.mvvm.MvvmActivity
import com.android.function.splash.SplashActivity

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // MVVM 디자인 패턴
        var go_mvvm:Button = findViewById(R.id.go_mvvm)
        go_mvvm.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,MvvmActivity::class.java)
            startActivity(intent)
        })

        val go_splash:Button = findViewById(R.id.go_splash)
        go_splash.setOnClickListener(View.OnClickListener {
            val intent = Intent(this,SplashActivity::class.java)
            startActivity(intent)
        })

    }
}