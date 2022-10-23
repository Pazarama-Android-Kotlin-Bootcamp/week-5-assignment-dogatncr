package com.example.simple_socialmedia.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simple_socialmedia.utils.Constants

/**
 ** */

@Entity(tableName = Constants.TABLE_USERS_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo(name="id") val id: Int?,
    @ColumnInfo(name = "address")  val address: String?,
    @ColumnInfo(name = "company")  val company: String?,
    @ColumnInfo(name="email") val email: String?,
    @ColumnInfo(name="name") val name: String?,
    @ColumnInfo(name="phone") val phone: String?,
    @ColumnInfo(name="username") val username: String?,
    @ColumnInfo(name="website") val website: String?
    )
