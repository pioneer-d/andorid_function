package com.android.function.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.android.function.R
import com.android.function.databinding.ActivityMvvmBinding

class MvvmActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMvvmBinding
    private val model: MvvmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_mvvm)

        mBinding.lifecycleOwner = this
        mBinding.viewModel = model


    }
}