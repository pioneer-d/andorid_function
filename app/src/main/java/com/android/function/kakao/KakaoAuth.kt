package com.android.function.kakao

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.net.toUri
import com.android.function.R
import com.bumptech.glide.Glide
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class KakaoAuth : AppCompatActivity() {

    //private var _binding: ActivityKakao? = null
    //private val binding get() = _binding!!

    var btn_start_kakao_login:ImageButton? = null
    var btn_start_kakao_logout:Button? = null
    var btn_start_kakao_unlink:Button? = null
    var tv_hashKey:TextView? = null
    var kakao_imageUrl:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kakao_auth)

        btn_start_kakao_login = findViewById(R.id.btn_start_kakao_login)
        btn_start_kakao_logout = findViewById(R.id.btn_start_kakao_logout)
        btn_start_kakao_unlink = findViewById(R.id.btn_start_kakao_unlink)
        tv_hashKey = findViewById(R.id.tv_hashKey)
        kakao_imageUrl = findViewById(R.id.kakao_imageUrl)


        /** HashKey확인 */
        val keyHash = Utility.getKeyHash(this)
        TextMsg(this, "HashKey: ${keyHash}")

        /** KakoSDK init */
        KakaoSdk.init(this, getString(R.string.kakao_app_key))

        /** Click_listener */
        btn_start_kakao_login!!.setOnClickListener(View.OnClickListener {
            kakaoLogin() //로그인
        })
        btn_start_kakao_logout!!.setOnClickListener(View.OnClickListener {
            kakaoLogout() //로그아웃
        })
        btn_start_kakao_unlink!!.setOnClickListener(View.OnClickListener {
            kakaoUnlink() //연결해제
        })
    }

    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        Log.d("진행 확인","kakaoLogin()")
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.d("진행 확인","카카오계정으로 로그인 실패 : ${error}")
                TextMsg(this,"카카오계정으로 로그인 실패 : ${error}")
                setLogin(false)
            } else if (token != null) {
                //TODO: 최종적으로 카카오로그인 및 유저정보 가져온 결과
                UserApiClient.instance.me { user, error ->
                    Log.d("진행 확인", "카카오계정으로 로그인 성공 \n\n " +
                            "token: ${token.accessToken} \n\n " +
                            "me: ${user}")
                    TextMsg(this,"카카오계정으로 로그인 성공 \n\n " +
                            "token: ${token.accessToken} \n\n " +
                            // "me: ${user} \n\n "+
                            "id : ${user?.id} \n\n" +
                            "nickName : ${user?.kakaoAccount?.profile?.nickname} \n\n"+
                            "profileImageUrl : ${user?.kakaoAccount?.profile?.profileImageUrl} \n\n" +
                            "email : ${user?.kakaoAccount?.email} \n\n" +
                            "gender : ${user?.kakaoAccount?.gender} \n\n" +
                            "age_range : ${user?.kakaoAccount?.ageRange} \n\n" +
                            "birthday : ${user?.kakaoAccount?.birthday} \n\n")

                        Glide.with(this).load(user?.kakaoAccount?.profile?.profileImageUrl.toString()).into(
                            kakao_imageUrl!!
                        )

                    setLogin(true)
                }
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.d("진행 확인", "카카오톡으로 로그인 실패 : ${error}")
                    TextMsg(this, "카카오톡으로 로그인 실패 : ${error}")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.d("진행 확인", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    TextMsg(this,"카카오톡으로 로그인 성공 ${token.accessToken}")
                    setLogin(true)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun kakaoLogout(){
        // 로그아웃
        UserApiClient.instance.logout { error ->
            if (error != null) {
                TextMsg(this, "로그아웃 실패. SDK에서 토큰 삭제됨: ${error}")
            }
            else {
                TextMsg(this, "로그아웃 성공. SDK에서 토큰 삭제됨")
                setLogin(false)
            }
        }
    }

    private fun kakaoUnlink(){
        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                TextMsg(this, "연결 끊기 실패: ${error}")
            }
            else {
                TextMsg(this, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
                setLogin(false)
            }
        }
    }

    private fun TextMsg(act: Activity, msg : String){
        tv_hashKey!!.text = msg
    }

    private fun setLogin(bool: Boolean){
        Log.d("진행 확인","setLogin()")
        btn_start_kakao_login!!.visibility = if(bool) View.GONE else View.VISIBLE
        btn_start_kakao_login!!.visibility = if(bool) View.VISIBLE else View.GONE
        btn_start_kakao_login!!.visibility = if(bool) View.VISIBLE else View.GONE
    }




}