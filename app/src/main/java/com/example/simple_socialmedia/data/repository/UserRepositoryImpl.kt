package com.example.simple_socialmedia.data.repository

import com.example.simple_socialmedia.data.local.database.UsersDatabase
import com.example.simple_socialmedia.data.remote.api.ApiService
import com.example.simple_socialmedia.data.model.Users
import retrofit2.Call

/**
 ** */

class UserRepositoryImpl constructor(
    private val apiService: ApiService,
    private val usersDatabase: UsersDatabase
) : UserRepository {


    override fun getUsers(): Call<List<Users>> {
        return apiService.getUsers()
    }
}