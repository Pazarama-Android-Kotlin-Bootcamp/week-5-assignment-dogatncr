package com.example.simple_socialmedia.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.simple_socialmedia.data.local.database.converter.DaoConverter
import com.example.simple_socialmedia.data.local.database.dao.UserDao
import com.example.simple_socialmedia.data.local.database.entity.UserEntity
import com.example.simple_socialmedia.utils.Constants

/**
 * User Database for keeping user formations in the Phone's memory with Room db.
 ** */

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
@TypeConverters(DaoConverter::class)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: UsersDatabase? = null

        fun getDatabase(context: Context): UsersDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, UsersDatabase::class.java, Constants.USERS_TABLE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()

    }
}