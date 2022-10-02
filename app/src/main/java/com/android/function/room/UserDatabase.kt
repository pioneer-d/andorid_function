package com.android.function.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)    // entity의 구조가 바뀔경우 버전으로 관리
// entity가 여러개인 경우
// @Database(entities = arrayOf(User::class, Student::class), version = 1)
// 데이터베이스 객체는 싱글톤으로 구현되어야 한다. (리소스 낭비가 클 수 있음.)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UserDatabase?{
            if (instance == null){
                synchronized(UserDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user-database"
                    ).build()
                }
            }
            return instance
        }
    }
}