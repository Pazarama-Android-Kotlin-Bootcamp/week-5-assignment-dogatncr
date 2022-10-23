package com.example.simple_socialmedia.data.repository

import com.example.simple_socialmedia.data.local.database.PostsDatabase
import com.example.simple_socialmedia.data.local.database.entity.PostEntity
import com.example.simple_socialmedia.data.remote.api.ApiService
import com.example.simple_socialmedia.data.model.Post
import retrofit2.Call

/**
 ** */

class PostRepositoryImpl constructor(
    private val apiService: ApiService,
    private val postsDatabase: PostsDatabase
) : PostRepository {
    override fun getPosts(): Call<List<Post>> {
        return apiService.getPosts()
    }

    override fun getPostById(id: Int): PostEntity? {
        return postsDatabase.postDao().getPostById(id.toString())
    }
    override fun getAllFavPosts(): List<PostEntity>? {
        return postsDatabase.postDao().getAllPosts()
    }

    override fun insertFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().insert(post)
    }

    override fun deleteFavoritePost(post: PostEntity) {
        return postsDatabase.postDao().delete(post)
    }
}