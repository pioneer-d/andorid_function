package com.android.function.iamport

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.function.R
import com.iamport.sdk.data.sdk.IamPortRequest
import com.iamport.sdk.data.sdk.PayMethod
import com.iamport.sdk.domain.core.Iamport
import java.util.*

class IamportActivity : AppCompatActivity() {

    val thisName = "IamportActivity"

    // 결제 요청할 데이터 구성
    val request = IamPortRequest(
//        pg = "kakaopay",                                 // PG 사
//        pay_method = PayMethod.kakaopay.name,          // 결제수단
//        name = "test 주문 명",                         // 주문명
//        merchant_uid = "test 주문번호",               // 주문번호
//        amount = "3000",                           // 결제금액
//        buyer_name = "홍길동"
        pg = "tosspay",
    pay_method = PayMethod.tosspay.name, //생략 가능
    merchant_uid= Date().time.toString(), // 상점에서 관리하는 주문 번호
    name = "주문명:결제테스트",
    amount = "14000",
    buyer_email = "iamport@siot.do",
    buyer_name = "구매자이름",
    buyer_tel = "010-1234-5678",
    buyer_addr = "서울특별시 강남구 삼성동",
    buyer_postcode = "123-456"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iamport)

        Iamport.create(this.application)

        // SDK 초기화
        Iamport.init(this)

        payment(request)

    }

    fun payment(request : IamPortRequest){  //userCode에 API key 넣어야함.
        Iamport.payment("",null,null, request,
            approveCallback = {
                /* (Optional) CHAI 최종 결제전 콜백 함수. */
                Log.e(thisName,"CHAI 최종 결제전 콜백 함수")
            },
            paymentResultCallback = {
            /* 최종 결제결과 콜백 함수. */
                Log.e(thisName,"최종 결제결과 콜백 함수")
            })
    }

    override fun onPause() {
        super.onPause()
        Iamport.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        Iamport.close()
    }


}