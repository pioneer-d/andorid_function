package com.android.function.mvvmWithRoom.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Profile::class], version = 1)
// entity가 여러개인 경우
// @Database(entities = arrayOf(User::class, Student::class), version = 1)

abstract class CleanDatabase: RoomDatabase() {
    // Dao를 여기서 묶어서 활용
    abstract fun cleanDao(): CleanDao

    companion object{
        private var instance: CleanDatabase? = null

        @Synchronized
        fun getInstance(context: Context): CleanDatabase?{
            if (instance == null){
                synchronized(CleanDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CleanDatabase::class.java,
                        "profile-database"
                    ).build()
                }
            }
            return instance
        }
    }

}