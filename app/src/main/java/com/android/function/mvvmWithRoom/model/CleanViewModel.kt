package com.android.function.mvvmWithRoom.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.function.mvvmWithRoom.model.room.CleanDatabase
import com.android.function.mvvmWithRoom.model.room.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CleanViewModel(application: Application) : AndroidViewModel(application) {

    var room: CleanDatabase = CleanDatabase.getInstance(application)!!
    val _count = MutableLiveData<Int>()
    val count: LiveData<Int>
        get() = _count


    init {
        CoroutineScope(Dispatchers.IO).launch {
            _count.postValue(room.cleanDao().getCount())
        }
        Log.e("init","_count : ${_count}")
    }

    fun getCount_1(){
        CoroutineScope(Dispatchers.IO).launch {
            _count.postValue(room.cleanDao().getCount())
            Log.e("getCount : ",room.cleanDao().getCount().toString())
        }
    }

    fun insert() {
        Log.e("insert!!","실행")
        val test_insert = Profile("동준영", 25, "IOS")
        CoroutineScope(Dispatchers.IO).launch {
            room.cleanDao().insert(test_insert)
        }

    }

    fun deleteAll() {
        Log.e("deleteAll!!","실행")
        CoroutineScope(Dispatchers.IO).launch {
            room.cleanDao().deleteAll()
        }
    }


}
