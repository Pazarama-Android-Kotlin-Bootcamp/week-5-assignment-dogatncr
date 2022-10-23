package com.example.simple_socialmedia.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.simple_socialmedia.data.local.database.base.BaseDao
import com.example.simple_socialmedia.data.local.database.entity.UserEntity
import com.example.simple_socialmedia.utils.Constants

/**
 ** */

@Dao
interface UserDao : BaseDao<UserEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_USERS_NAME}")
    fun getAllUsers(): List<UserEntity>

    @Query("DELETE FROM ${Constants.TABLE_USERS_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_USERS_NAME} WHERE id = :UserId")
    fun getUserById(UserId: String): UserEntity?

}