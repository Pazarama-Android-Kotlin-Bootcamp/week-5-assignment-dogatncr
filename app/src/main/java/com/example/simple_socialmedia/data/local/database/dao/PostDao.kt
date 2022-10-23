package com.example.simple_socialmedia.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.simple_socialmedia.data.local.database.base.BaseDao
import com.example.simple_socialmedia.data.local.database.entity.PostEntity
import com.example.simple_socialmedia.utils.Constants

/**
 ** */

@Dao
interface PostDao : BaseDao<PostEntity> {
    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME}")
    fun getAllPosts(): List<PostEntity>

    @Query("DELETE FROM ${Constants.TABLE_POST_NAME}")
    fun deleteAll()

    @Query("SELECT * FROM ${Constants.TABLE_POST_NAME} WHERE postId = :postId")
    fun getPostById(postId: String): PostEntity?

}