package com.example.simple_socialmedia.data.model

import com.google.gson.annotations.SerializedName

/**
 * * 8.10.2022.
 */

data class Post(
    @SerializedName("body")
    val body: String?,
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String?
)

data class PostDTO(
    val body: String?,
    val userId: Int?,
    val postId: Int?,
    val title: String?,
    var isFavorite: Boolean=true,
)