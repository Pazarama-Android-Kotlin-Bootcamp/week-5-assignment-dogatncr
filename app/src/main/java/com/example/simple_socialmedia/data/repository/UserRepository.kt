package com.example.simple_socialmedia.data.repository

import com.example.simple_socialmedia.data.model.Users
import retrofit2.Call

/**
 ** */

interface UserRepository {
    fun getUsers(): Call<List<Users>>

}