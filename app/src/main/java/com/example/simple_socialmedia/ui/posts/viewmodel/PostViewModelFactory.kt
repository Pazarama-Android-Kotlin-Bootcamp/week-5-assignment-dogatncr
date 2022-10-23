package com.example.simple_socialmedia.ui.posts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simple_socialmedia.data.repository.PostRepository

/**
 ** */

@Deprecated("Use ViewModelFactory from Hilt")
class PostViewModelFactory(private val postRepository: PostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostsViewModel(postRepository) as T
    }
}