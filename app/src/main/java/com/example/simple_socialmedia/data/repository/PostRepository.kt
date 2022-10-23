package com.example.simple_socialmedia.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.simple_socialmedia.data.local.database.entity.PostEntity
import com.example.simple_socialmedia.data.model.DataState
import com.example.simple_socialmedia.data.model.Post
import com.example.simple_socialmedia.data.model.PostDTO
import retrofit2.Call

/**
 ** */

interface PostRepository {
    fun getPosts(): Call<List<Post>>
    fun getPostById(id: Int): PostEntity?
    fun getAllFavPosts(): List<PostEntity>?
    fun insertFavoritePost(post: PostEntity)
    fun deleteFavoritePost(post: PostEntity)
}