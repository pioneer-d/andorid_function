package com.android.function

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import com.android.function.accessRight.AccessRightActivity
import com.android.function.barcode.MainActivity3
import com.android.function.databinding.ActivityMainBinding
import com.android.function.dialog_cardView.Dialog_CardView
import com.android.function.getLocation.LocationActivity
import com.android.function.iamport.IamportActivity
import com.android.function.kakao.KakaoAuth
import com.android.function.mvvm.MvvmActivity
import com.android.function.network.NetworkConfirm
import com.android.function.recyclerview_api.MainActivity
import com.android.function.splash.SplashActivity
//import com.kakao.sdk.common.KakaoSdk
//import com.kakao.sdk.common.util.Utility

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding

//    var go_mvvm:Button? = null
//    var go_splash:Button? = null
//    var go_access_right:Button? = null
//    var go_location:Button? = null
//    var go_network:Button? = null
//    var go_dialog:Button? = null
//    var go_kakao:Button? = null
//    var go_Recycler:Button? = null
//    var go_barcode:Button? = null
//    var go_iamport:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goMvvm.setOnClickListener(this)
        binding.goSplash.setOnClickListener(this)
        binding.goAccessRight.setOnClickListener(this)
        binding.goLocation.setOnClickListener(this)
        binding.goNetwork.setOnClickListener(this)
        binding.goDialog.setOnClickListener(this)
        binding.goKakao.setOnClickListener(this)
        binding.goRecycler.setOnClickListener(this)
        binding.goBarcode.setOnClickListener(this)
        binding.goIamport.setOnClickListener(this)
//        go_mvvm = findViewById(R.id.go_mvvm)
//        go_mvvm!!.setOnClickListener(this)
//
//        go_splash = findViewById(R.id.go_splash)
//        go_splash!!.setOnClickListener(this)
//
//        go_access_right = findViewById(R.id.go_access_right)
//        go_access_right!!.setOnClickListener(this)
//
//        go_location = findViewById(R.id.go_location)
//        go_location!!.setOnClickListener(this)
//
//        go_network = findViewById(R.id.go_network)
//        go_network!!.setOnClickListener(this)
//
//        go_dialog = findViewById(R.id.go_dialog)
//        go_dialog!!.setOnClickListener(this)
//
//        go_kakao = findViewById(R.id.go_kakao)
//        go_kakao!!.setOnClickListener(this)
////        KakaoSdk.init(this,applicationContext.getString(R.string.kakao_app_key))
//
//        go_Recycler = findViewById(R.id.go_Recycler)
//        go_Recycler!!.setOnClickListener(this)
//
//        go_barcode = findViewById(R.id.go_barcode)
//        go_barcode!!.setOnClickListener(this)
//
//        go_iamport = findViewById(R.id.go_iamport)
//        go_iamport!!.setOnClickListener(this)


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
                R.id.go_barcode -> {
                    goActivity(MainActivity3())
                }
                R.id.go_iamport -> {
                    goActivity(IamportActivity())
                }

            }
        }

    fun goActivity(where : Activity){
        val intent = Intent(this,where::class.java)
        startActivity(intent)
    }


}