package com.example.simple_socialmedia.data.di

import com.example.simple_socialmedia.data.local.database.PostsDatabase
import com.example.simple_socialmedia.data.remote.api.ApiService
import com.example.simple_socialmedia.data.repository.PostRepository
import com.example.simple_socialmedia.data.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * * 17.10.2022.
 */

@Module
@InstallIn(ViewModelComponent::class)
class PostsModule {
    // Api Service Class
    // Repository and DataSource Impl
    // Database

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providePostRepository(apiService: ApiService, postsDatabase: PostsDatabase) : PostRepository {
        return PostRepositoryImpl(apiService, postsDatabase)
    }

}