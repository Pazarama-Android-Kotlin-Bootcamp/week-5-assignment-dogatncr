package com.example.simple_socialmedia.ui.favorites.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simple_socialmedia.data.local.database.entity.PostEntity
import com.example.simple_socialmedia.data.model.DataState
import com.example.simple_socialmedia.data.model.PostDTO
import com.example.simple_socialmedia.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * * 15.10.2022.
 */

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private var _postFav = MutableLiveData<DataState<List<PostDTO>?>>()
    val postFav: LiveData<DataState<List<PostDTO>?>>
        get() = _postFav

    private val _eventStateLiveData = MutableLiveData<PostViewEvent>()
    val eventStateLiveData: LiveData<PostViewEvent>
        get() = _eventStateLiveData

    init {
        getFavPosts()
    }

    fun getFavPosts() {
        _postFav.postValue(DataState.Loading())
        postRepository.getAllFavPosts().let{
            if (it != null) {
                _postFav.postValue(DataState.Success(it.map { safePost ->
                    PostDTO(
                        id = safePost.id,
                        title = safePost.postTitle,
                        body = safePost.postBody,
                        userId = safePost.postId?.toInt(),
                        //isFavorite = true
                    )
                }))
            }
        }
    }

    fun onFavoritePost(post: PostDTO) {
        postRepository.getPostById(post.id ?: 0)?.let {
            postRepository.deleteFavoritePost(
                PostEntity(
                    id=post.id,
                    postId = post.userId.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
        } ?: kotlin.run {
            postRepository.insertFavoritePost(
                PostEntity(
                    id=post.id,
                    postId = post.userId.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
        }
    }

    private fun isExists(postId: Int?): Boolean {
        postId?.let {
            postRepository.getPostById(it)?.let {
                return true
            }
        }
        return false
    }
}

sealed class PostViewEvent {
    object NavigateToDetail : PostViewEvent()
    class ShowMessage(val message: String?) : PostViewEvent()
}