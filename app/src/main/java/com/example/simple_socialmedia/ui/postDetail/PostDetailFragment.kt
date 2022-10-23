package com.example.simple_socialmedia.ui.postDetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simple_socialmedia.data.model.Comment
import com.example.simple_socialmedia.data.remote.api.ApiClient
import com.simple_socialmedia.databinding.FragmentPostDetailBinding

import com.example.simple_socialmedia.ui.loadingprogress.LoadingProgressBar
import com.example.simple_socialmedia.ui.postDetail.adapter.PostDetailAdapter

import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class PostDetailFragment : Fragment() {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentPostDetailBinding
    //Post detail fragment which contains comments of the post

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)
        arguments?.let {
            it.getString("id")?.let { it1 -> getPostDetails(it1) }
            binding.postOwner.text= it.getString("title")
            binding.caption.text= it.getString("body")
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())
    }

    private fun getPostDetails(id:String){
        //gathering comment data of our post with postId from api.

        ApiClient.getApiService().getPostDetail(id).enqueue(object : retrofit2.Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.d("hata","fail")
            }

            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    val commentList = response.body()
                    binding.commentsList.adapter = commentList?.let { PostDetailAdapter(it) }

                    }
                }
        })
    }

}


