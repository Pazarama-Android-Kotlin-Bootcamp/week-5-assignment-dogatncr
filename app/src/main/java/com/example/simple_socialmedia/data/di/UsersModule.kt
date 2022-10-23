package com.example.simple_socialmedia.data.di

import com.example.simple_socialmedia.data.local.database.UsersDatabase
import com.example.simple_socialmedia.data.remote.api.ApiService
import com.example.simple_socialmedia.data.repository.UserRepository
import com.example.simple_socialmedia.data.repository.UserRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {
    // Api Service Class
    // Repository and DataSource Impl
    // Database

    @Provides
    fun provideUserRepository(apiService: ApiService, usersDatabase: UsersDatabase) : UserRepository {
        return UserRepositoryImpl(apiService, usersDatabase)
    }
}