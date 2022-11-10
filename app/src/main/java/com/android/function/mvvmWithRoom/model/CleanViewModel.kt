package com.android.function.mvvmWithRoom.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CleanViewModel: ViewModel() {

    var count = MutableLiveData<Int>()

}