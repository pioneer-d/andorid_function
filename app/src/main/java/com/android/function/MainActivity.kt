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
import com.android.function.mvvmWithRoom.view.CleanArchitecture
import com.android.function.network.NetworkConfirm
import com.android.function.recyclerview_api.MainActivity
import com.android.function.room.RoomActivity
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
        binding.goRoom.setOnClickListener(this)
        binding.goClean.setOnClickListener(this)

    }

        override fun onClick(v: View?) {
            when(v?.id){
                R.id.go_mvvm -> goActivity(MvvmActivity())  // MVVM 디자인 패턴
                R.id.go_splash -> goActivity(SplashActivity())    // Splash 화면
                R.id.go_access_right -> goActivity(AccessRightActivity())   // Permission 확인
                R.id.go_location -> goActivity(LocationActivity())  // 위치 정보 get
                R.id.go_network -> goActivity(NetworkConfirm())    // 네트워크 상태 확인
                R.id.go_dialog -> goActivity(Dialog_CardView())   // Dialog 출력
                R.id.go_kakao -> goActivity(KakaoAuth()) // Kakao 로그인 Api
                R.id.go_Recycler -> goActivity(MainActivity()) // RecyclerView 구현
                R.id.go_barcode -> goActivity(MainActivity3()) // barcode
                R.id.go_iamport -> goActivity(IamportActivity())   // Iamport
                R.id.go_room -> goActivity(RoomActivity())
                R.id.go_clean -> goActivity(CleanArchitecture())    //Clean Architecture


            }
        }

    fun goActivity(where : Activity){
        val intent = Intent(this,where::class.java)
        startActivity(intent)
    }


}