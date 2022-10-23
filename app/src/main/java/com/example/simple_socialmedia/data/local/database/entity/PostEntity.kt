package com.example.simple_socialmedia.data.local.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.simple_socialmedia.utils.Constants

/**
 ** */
//our entitiy for room database
@Entity(tableName = Constants.TABLE_POST_NAME)
data class PostEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "postId") val postId: Int?,
    @ColumnInfo(name = "userId") val userId: Int?,
    @ColumnInfo(name = "postTitle") val postTitle: String?,
    @ColumnInfo(name = "postBody") val postBody: String?,
)
