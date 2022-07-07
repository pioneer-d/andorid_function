package com.android.function.dialog_cardView

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.function.R

class Dialog_CardView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_card_view)

        var alert_dialog: Button = findViewById(R.id.alert_dialog)
        alert_dialog.setOnClickListener(View.OnClickListener {
            alertDialog()
        })

    }

    fun alertDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_cardview)
        dialog.setCancelable(false) // 취소 버튼으로 dialog 삭제 불가
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))     // 모서리, 꼭짓점 주변과 맞추기.

        dialog.show()   // dialog 실행

        // dialog 내부 TextView
        val agree:TextView = dialog.findViewById(R.id.dialog_agree)
        val disAgree:TextView = dialog.findViewById(R.id.dialog_disAgree)

        agree.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"확인 처리",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        })

        disAgree.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"취소 처리", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        })

    }


}