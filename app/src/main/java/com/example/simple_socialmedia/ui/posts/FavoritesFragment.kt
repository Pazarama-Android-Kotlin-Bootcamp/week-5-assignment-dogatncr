package com.example.simple_socialmedia.ui.posts

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.simple_socialmedia.R
import com.example.simple_socialmedia.data.model.DataState
import com.example.simple_socialmedia.data.model.PostDTO
import com.simple_socialmedia.databinding.FragmentFavoritePostsBinding
import com.example.simple_socialmedia.ui.loadingprogress.LoadingProgressBar
import com.example.simple_socialmedia.ui.posts.adapter.OnPostClickListener
import com.example.simple_socialmedia.ui.posts.adapter.PostsAdapter
import com.example.simple_socialmedia.ui.posts.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

//Fragment for showing favorite posts
@AndroidEntryPoint
class FavoritesFragment : Fragment(), OnPostClickListener {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentFavoritePostsBinding
    //private val viewModel by viewModels<PostsViewModel>()
    private val sharedViewModel: PostsViewModel by activityViewModels()
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoritePostsBinding.inflate(inflater, container, false)
        binding.rvFavPostsList.adapter= PostsAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())
        navController = findNavController()
        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        //viewModel.getFavPosts()
        //Observing favorite Posts data and updating view on change.
        sharedViewModel.postFav.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.rvFavPostsList.adapter = PostsAdapter(this).apply {
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


    }

    override fun onPostClick(post: PostDTO) {
        navController.navigate(R.id.action_favoritesFragment_to_postDetailFragment, Bundle().apply {
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


