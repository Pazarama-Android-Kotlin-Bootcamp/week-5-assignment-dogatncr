package com.example.simple_socialmedia.ui.posts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.simple_socialmedia.R
import com.example.simple_socialmedia.data.model.DataState
import com.example.simple_socialmedia.data.model.PostDTO
import com.simple_socialmedia.databinding.FragmentPostsBinding
import com.example.simple_socialmedia.ui.loadingprogress.LoadingProgressBar
import com.example.simple_socialmedia.ui.posts.adapter.OnPostClickListener
import com.example.simple_socialmedia.ui.posts.adapter.PostsAdapter
import com.example.simple_socialmedia.ui.posts.viewmodel.PostViewEvent
import com.example.simple_socialmedia.ui.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment(), OnPostClickListener {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentPostsBinding
    //private val viewModel by viewModels<PostsViewModel>()
    private val sharedViewModel: PostsViewModel by activityViewModels()
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())
        navController = findNavController()
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //observing post live data for any changes and updating view on change.
        sharedViewModel.postLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.rvPostsList.adapter = PostsAdapter(this@PostsFragment).apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }

        sharedViewModel.eventStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is PostViewEvent.ShowMessage -> {}
                is PostViewEvent.NavigateToDetail -> {}
            }
        }

    }
    /**
     * Clicking a post opens postDetail page which includes comments and details from itself*/
    override fun onPostClick(post: PostDTO) {
        navController.navigate(R.id.action_postsFragment_to_postDetailFragment, Bundle().apply {
            putString("id", post.postId.toString())
            putString("title",post.title)
            putString("body",post.body)
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onLikeClick(post: PostDTO) {
        sharedViewModel.onFavoritePost(post)
    }
}


