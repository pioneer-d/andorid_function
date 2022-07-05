package com.android.function.accessRight

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.function.R


class AccessRightActivity : AppCompatActivity() {

    // 퍼미션 응답 처리 코드
    val multiplePermissionsCode = 100

    // 확인 할 퍼미션 리스트
    val requiredPermisisons = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.ACCESS_FINE_LOCATION
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_right)

        val get_right:Button = findViewById(R.id.get_right)
        get_right.setOnClickListener(View.OnClickListener {
            checkPermissions()
        })

    }

    fun checkPermissions(){
        // 거절했거나, 아직 수락하지 않은 권한을 저장할 문자열 배열 List
        var rejectedPermissionList = ArrayList<String>()

        // 필요한 퍼미션을 하나씩 체크
        for(permission in requiredPermisisons){
            if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                // 거절한 퍼미션 입력
                rejectedPermissionList.add(permission)
            }
        }

        // 거절했거나 아직 수락하지 않은 권한에 대해 요청.
        if (rejectedPermissionList.isNotEmpty()){
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(this,rejectedPermissionList.toArray(array),multiplePermissionsCode)
        }

        // 모든 권한을 허용한 경우
        if (rejectedPermissionList.isEmpty()){
            Toast.makeText(this,"모든 권한을 허용 했음.",Toast.LENGTH_SHORT).show()
        }

    }

    // 권한 요청 결과 처리 함수
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            multiplePermissionsCode -> {    // 특정 응답 코드에 대해서
                if (grantResults.isNotEmpty()){     // 퍼미션에 대한 결과값 (허용, 거절)
                    for ((i,permissions) in permissions.withIndex()){
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED){  // 허가 안한 퍼미션 처리
                            Toast.makeText(this,"허용안한 퍼미션 : $permissions",Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }



}