package com.android.function

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.android.function.accessRight.AccessRightActivity
import com.android.function.dialog_cardView.Dialog_CardView
import com.android.function.getLocation.LocationActivity
import com.android.function.kakao.KakaoAuth
import com.android.function.mvvm.MvvmActivity
import com.android.function.network.NetworkConfirm
import com.android.function.recyclerview_api.MainActivity
import com.android.function.splash.SplashActivity
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var go_mvvm:Button? = null
    var go_splash:Button? = null
    var go_access_right:Button? = null
    var go_location:Button? = null
    var go_network:Button? = null
    var go_dialog:Button? = null
    var go_kakao:Button? = null
    var go_Recycler:Button? = null

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

        go_dialog = findViewById(R.id.go_dialog)
        go_dialog!!.setOnClickListener(this)

        go_kakao = findViewById(R.id.go_kakao)
        go_kakao!!.setOnClickListener(this)
        KakaoSdk.init(this,"9d372aeedfd14c280bd3a506c7c88302")

        go_Recycler = findViewById(R.id.go_Recycler)
        go_Recycler!!.setOnClickListener(this)



//        var keyHash = Utility.getKeyHash(this)
//        Log.d("Kakao Hash Key : ","$keyHash")

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
                R.id.go_location -> {   // 위치 정보 get
                    goActivity(LocationActivity())
                }
                R.id.go_network -> {    // 네트워크 상태 확인
                    goActivity(NetworkConfirm())
                }
                R.id.go_dialog -> {     // Dialog 출력
                    goActivity(Dialog_CardView())
                }
                R.id.go_kakao -> {      // Kakao 로그인 Api
                    goActivity(KakaoAuth())
                }
                R.id.go_Recycler -> {   // RecyclerView 구현
                    goActivity(MainActivity())
                }

            }
        }

    fun goActivity(where : Activity){
        val intent = Intent(this,where::class.java)
        startActivity(intent)
    }


}