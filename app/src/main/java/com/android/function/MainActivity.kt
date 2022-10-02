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