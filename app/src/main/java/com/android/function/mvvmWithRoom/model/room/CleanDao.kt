package com.android.function.mvvmWithRoom.model.room

import androidx.room.*

@Dao
interface CleanDao {
    // DataBase 싱글톤에 묶인다.

    @Insert
    fun insert(user: Profile)

    @Update
    fun update(user: Profile)

    @Delete
    fun delete(user: Profile)

    @Query("SELECT count(*) FROM Profile")
    fun getCount(): Int

    @Query("SELECT * FROM Profile")
    fun getAll(): List<Profile>

    @Query("DELETE FROM Profile WHERE id = :id")
    fun deleteProfileById(id: Int)
}