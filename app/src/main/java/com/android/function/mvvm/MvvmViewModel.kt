package com.android.function.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//class MvvmViewModel(application: Application) : AndroidViewModel(Application()) {
class MvvmViewModel : ViewModel() {

    // ViewModel을 상속 받아도 되고, AndroidViewModel을 상속 받아도 되는데,
    // AndroidViewModel은 Context를 활용하는 경우에 사용하고, 권장은 ViewModel임.
    // ViewModel을 상속받을 경우 class MainViewModel():ViewModel(){}

    // LiveData
    // 값이 변경되는 경우 MutableLiveData로 선언
    // ViewModel에 LiveData가 있는 모습.
    var count = MutableLiveData<Int>()

    init {
        count.value = 0
    }

    fun increase(){
        count.value = count.value?.plus(1)
    }

    fun decrease(){
        count.value = count.value?.minus(1)
    }

}