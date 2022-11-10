package com.android.function.mvvmWithRoom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.android.function.R
import com.android.function.databinding.ActivityCleanArchitectureBinding
import com.android.function.mvvmWithRoom.model.CleanViewModel

class CleanArchitecture : AppCompatActivity() {

    private lateinit var binding: ActivityCleanArchitectureBinding
    private val model: CleanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_clean_architecture)

        // DataBinding 설정
        binding.lifecycleOwner = this
        // ViewModel 설정
        binding.viewModel = model
    }

    // Room, RecyclerView 활용해서
    // 입력한 값이 리스트에 LiveData로 추출되고,
    // 삭제한 값이 리스트에 LivaData로 반영되는 프로젝트 만들기.

}