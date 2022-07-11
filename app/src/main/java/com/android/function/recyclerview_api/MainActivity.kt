package com.android.function.recyclerview_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.function.R
import com.android.function.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2)
        binding.activity = this@MainActivity

        setRcv()
    }

    fun setRcv(){
        val profileAdapter = ProfileAdapter(this)
        binding.mainRcv.layoutManager = LinearLayoutManager(this)
        binding.mainRcv.adapter = profileAdapter
        profileAdapter.data = listOf(
            ProfileData(profile = "R.drawable.dog", name = "Dong", age = 25),
            ProfileData(profile = "R.drawable.dog", name = "Lee", age = 25)
        )
        profileAdapter.notifyDataSetChanged()
    }
}