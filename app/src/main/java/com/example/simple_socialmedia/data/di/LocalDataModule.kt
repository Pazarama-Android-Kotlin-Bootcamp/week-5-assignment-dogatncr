package com.example.simple_socialmedia.data.di

import android.content.Context
import com.example.simple_socialmedia.data.local.database.PostsDatabase
import com.example.simple_socialmedia.data.local.database.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * * 17.10.2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    // Room Database
    // Dao
    //DataStoreManager
    //SharedPreferences

    @Provides
    @Singleton
    fun providePostsDatabase(@ApplicationContext appContext: Context): PostsDatabase {
        return PostsDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun providePostDao(db: PostsDatabase) = db.postDao()

    @Provides
    @Singleton
    fun provideUsersDatabase(@ApplicationContext appContext: Context): UsersDatabase {
        return UsersDatabase.getDatabase(appContext)
    }

    @Singleton
    @Provides
    fun provideUserDao(db: UsersDatabase) = db.userDao()
}