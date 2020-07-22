package com.aman.roommvvmcoroutinenavigation.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Column info name will be used while saving data in database
//column name will be what is defined inside the ColumnInfo

@Entity
class User(
    val userName: String,

    @ColumnInfo(name = "password_hash")
    val passwordHash: Int,

    val info: String
) {
    //Define other information required here like primary key and all
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}