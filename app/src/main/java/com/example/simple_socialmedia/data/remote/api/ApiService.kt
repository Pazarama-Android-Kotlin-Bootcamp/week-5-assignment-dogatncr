package com.example.simple_socialmedia.data.remote.api

import com.example.simple_socialmedia.data.model.Comment
import com.example.simple_socialmedia.data.model.Post
import com.example.simple_socialmedia.data.model.Users
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

//Api services for communication with jsonplaceholder services
interface ApiService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    @GET("posts/{id}/comments")
    fun getPostDetail(@Path("id") id: String): Call<List<Comment>>

    @GET("users")
    fun getUsers(): Call<List<Users>>

    @DELETE("posts/{id}")
    fun deletePost(@Path("{id}") id: String): Call<Post>
}
