package com.android.function.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.function.R
import com.android.function.databinding.ActivityRoomBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {

    lateinit var binding: ActivityRoomBinding
    lateinit var room: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 싱글톤 객체 얻어오기
        room = UserDatabase.getInstance(this)!!
        val user_1 = User("동준영", "25", "3975")

        binding.insert.setOnClickListener(View.OnClickListener {
            // 비동기로 실행해야 한다!
            CoroutineScope(Dispatchers.IO).launch {
                room.userDao().insert(user_1)
            }
        })

        binding.getAll.setOnClickListener(View.OnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.e("start : ","@@@@@@@@@@@@@@@@@@@@@")
                for (i in 0 until room.userDao().getAll().size)
                    Log.e("getAll : ",room.userDao().getAll()[i].toString())
            }
        })

        binding.delete.setOnClickListener(View.OnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                Log.e("delete()","delete room data with name == 동준영")
                room.userDao().deleteUserByName("동준영")
            }
        })



    }



}
