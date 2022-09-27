package com.android.function.iamport

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.function.R
import com.iamport.sdk.data.sdk.IamPortRequest
import com.iamport.sdk.domain.core.Iamport
import java.util.*

class IamportActivity : AppCompatActivity() {

    var pg_spinner: Spinner? = null
    var payment_method_spinner: Spinner? = null


    val thisName = "IamportActivity"

    // 결제 요청할 데이터 구성

    var pg: String = ""
    var method: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iamport)

        Iamport.create(this.application)
        // SDK 초기화
        Iamport.init(this)

        pg_spinner = findViewById(R.id.pg_spinner)
        payment_method_spinner = findViewById<Spinner>(R.id.payment_method_spinner)


        pg_spinner!!.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.pg_spinner,
            android.R.layout.simple_spinner_item
        )
        payment_method_spinner!!.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.payment_method_spinner,
            android.R.layout.simple_spinner_item
        )

        pg_spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                when (position) {
                    0 -> { pg = "chai" }
                    1 -> { pg = "kcp" }
                    2 -> { pg = "html5_inicis" }
                    3 -> { pg = "kcp_billing" }
                    4 -> { pg = "uplus" }
                    5 -> { pg = "jtnet" }
                    6 -> { pg = "kakaopay" }
                    7 -> { pg = "nice" }
                    8 -> { pg = "danal" }
                    9 -> { pg = "danal_tpay" }
                    10 -> { pg = "kicc" }
                    11 -> { pg = "paypal" }
                    12 -> { pg = "mobilians" }
                    13 -> { pg = "payco" }
                    14 -> { pg = "eximbay" }
                    15 -> { pg = "settle" }
                    16 -> { pg = "settle_firm" }
                    17 -> { pg = "naverpay" }
                    18 -> { pg = "smilepay" }
                    19 -> { pg = "payple" }
                    20 -> { pg = "alipay" }
                    21 -> { pg = "bluewalnut" }
                    22 -> { pg = "tosspay" }
                    23 -> { pg = "smartro" }
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext, "PG사를 선택해주세요", Toast.LENGTH_SHORT).show()
            }

        }

        payment_method_spinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position){
                    0 -> {method = "card"}
                    1 -> {method = "trans"}
                    2 -> {method = "vbank"}
                    3 -> {method = "phone"}
                    4 -> {method = "samsung"}
                    5 -> {method = "kpay"}
                    6 -> {method = "kakaopay"}
                    7 -> {method = "payco"}
                    8 -> {method = "lpay"}
                    9 -> {method = "ssgpay"}
                    10 -> {method = "tosspay"}
                    11 -> {method = "cultureland"}
                    12 -> {method = "smartculture"}
                    13 -> {method = "happymoney"}
                    14 -> {method = "booknlife"}
                    15 -> {method = "point"}
                    16 -> {method = "unionpay"}
                    17 -> {method = "alipay"}
                    18 -> {method = "tenpay"}
                    19 -> {method = "wechat"}
                    20 -> {method = "molpay"}
                    21 -> {method = "paysbuy"}
                    22 -> {method = "naverpay"}
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        val payment_button = findViewById<Button>(R.id.payment_button)
        payment_button.setOnClickListener(View.OnClickListener {
            val request = IamPortRequest(
                pg = pg,    // PG 사
                pay_method = method, //결제수단
                merchant_uid = Date().time.toString(), // 상점에서 관리하는 주문 번호
                name = "주문명:결제테스트",                 // 주문명
                amount = "14000",                      //결제금액
                buyer_email = "iamport@siot.do",
                buyer_name = "구매자이름",
                buyer_tel = "010-1234-5678",
                buyer_addr = "서울특별시 강남구 삼성동",
                buyer_postcode = "123-456"
            )
            payment(request)
        })

    }

    fun payment(request: IamPortRequest) {  // 가맹점 식별코드 입력해야함.
//        Iamport.payment(applicationContext.getString(R.string.iamport_user_code), null, null, request,
//            approveCallback = {
//                /* (Optional) CHAI 최종 결제전 콜백 함수. */
//                Log.e(thisName, "CHAI 최종 결제전 콜백 함수")
//            },
//            paymentResultCallback = {
//                /* 최종 결제결과 콜백 함수. */
//                Log.e(thisName, "최종 결제결과 콜백 함수")
//            })
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