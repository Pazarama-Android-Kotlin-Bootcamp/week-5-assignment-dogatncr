package com.example.simple_socialmedia.ui.posts.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simple_socialmedia.data.local.database.entity.PostEntity
import com.example.simple_socialmedia.data.model.DataState
import com.example.simple_socialmedia.data.model.Post
import com.example.simple_socialmedia.data.model.PostDTO
import com.example.simple_socialmedia.data.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

/**
 * * 15.10.2022.
 */

@HiltViewModel
class PostsViewModel @Inject constructor(private val postRepository: PostRepository) : ViewModel() {
    private var _postLiveData = MutableLiveData<DataState<List<PostDTO>?>>()
    val postLiveData: LiveData<DataState<List<PostDTO>?>>
        get() = _postLiveData
    private var _postCacheData = MutableLiveData<List<PostDTO>?>()
    val postCacheData: LiveData<List<PostDTO>?>
        get() = _postCacheData

    private val _eventStateLiveData = MutableLiveData<PostViewEvent>()
    val eventStateLiveData: LiveData<PostViewEvent>
        get() = _eventStateLiveData

    init {
        getPosts()
    }
    //Function for gathering all posts from api with retrofit and updating live data of the fragment.
    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            _postLiveData.postValue(DataState.Loading())
            delay(500)
            postRepository.getPosts().enqueue(object : Callback<List<Post>> {
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            val updatePostData = it.map { safePost ->
                                PostDTO(
                                    id = safePost.id,
                                    title = safePost.title,
                                    body = safePost.body,
                                    userId = safePost.userId,
                                    isFavorite = runBlocking { isExists(safePost.id)}
                                )
                            }
                            _postLiveData.postValue(DataState.Success(updatePostData))
                            _postCacheData.value=updatePostData


                        } ?: kotlin.run {
                            _postLiveData.postValue(DataState.Error("Data Empty"))
                        }
                    } else {
                        _postLiveData.postValue(DataState.Error(response.message()))
                    }
                }

                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    _postLiveData.postValue(DataState.Error(t.message.toString()))
                    _eventStateLiveData.postValue(PostViewEvent.ShowMessage(t.message.toString()))
                }
            })
        }
    }
    //if our post is alredy in our room db, we delete if not we are adding to room db
    fun onFavoritePost(post: PostDTO) {
        post.id?.let{safePostId ->
        postRepository.getPostById(safePostId)?.let {
            postRepository.deleteFavoritePost(
                PostEntity(
                    postId = post.id.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
            _postLiveData.postValue(DataState.Success(prepareUpdatePostData(post,isDelete =true)))
            //_postLiveData.value=DataState.Success(prepareUpdatePostData(post,isDelete =true))
        } ?: kotlin.run {
            postRepository.insertFavoritePost(
                PostEntity(
                    postId = post.id.toString(),
                    postTitle = post.title,
                    postBody = post.body
                )
            )
            _postLiveData.postValue(DataState.Success(prepareUpdatePostData(post,isDelete=false)))
            //_postLiveData.value=DataState.Success(prepareUpdatePostData(post))
        }
    }
    }

    private fun prepareUpdatePostData(post: PostDTO, isDelete:Boolean): List<PostDTO>? {
       _postCacheData.value?.map{
           if(post.id == it.id){
               it.isFavorite=!isDelete
               //it.copy(isFavorite=!isDelete)
           }
        else{
            it
        }
       }
        return _postCacheData.value
    }

    private suspend fun isExists(postId: Int?): Boolean {
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