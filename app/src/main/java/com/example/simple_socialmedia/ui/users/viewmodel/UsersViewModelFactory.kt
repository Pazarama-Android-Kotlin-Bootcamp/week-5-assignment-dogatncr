package com.example.simple_socialmedia.ui.users.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simple_socialmedia.data.repository.UserRepository


/**
 ** */

@Deprecated("Use ViewModelFactory from Hilt")
class UsersViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(userRepository) as T
    }
}