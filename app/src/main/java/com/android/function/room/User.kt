package com.android.function.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
// @Entity(tableName="userProfile")
// 이렇게 명시하지 않으면 클래스 이름이 Table의 이름이 됨.
data class User(
    // 칼럼들 나열
    var name: String,
    var age: String,
    var phone: String
) {
    @PrimaryKey(autoGenerate = true)    // 자동으로 값을 생성해주는지 여부
    var id: Int = 0
}