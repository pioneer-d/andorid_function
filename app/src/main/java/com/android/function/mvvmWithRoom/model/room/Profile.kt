package com.android.function.mvvmWithRoom.model.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Profile(
    var name: String,
    var age: Int,
    var phone: String
){
    // 키값       자동 시퀀스 증가
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
