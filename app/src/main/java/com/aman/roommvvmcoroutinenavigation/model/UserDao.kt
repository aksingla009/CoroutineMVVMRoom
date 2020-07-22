package com.aman.roommvvmcoroutinenavigation.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * from user where userName = :userName")
    fun getUser(userName: String): User

    @Query("DELETE from user where id = :id")
    suspend fun deleteUser(id: Long)

}