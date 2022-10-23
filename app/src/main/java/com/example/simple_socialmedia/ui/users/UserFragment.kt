package com.example.simple_socialmedia.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.example.simple_socialmedia.data.model.DataState
import com.example.simple_socialmedia.data.model.UserDTO
import com.simple_socialmedia.databinding.FragmentUserBinding
import com.example.simple_socialmedia.ui.loadingprogress.LoadingProgressBar
import com.example.simple_socialmedia.ui.users.adapter.OnUserClickListener
import com.example.simple_socialmedia.ui.users.adapter.UsersAdapter
import com.example.simple_socialmedia.ui.users.viewmodel.UserViewEvent

import com.example.simple_socialmedia.ui.users.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
* User fragment for showing users from jsonplaceholder api in a list */
@AndroidEntryPoint
class UserFragment : Fragment(), OnUserClickListener {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentUserBinding
    private val viewModel by viewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())
        binding.viewModel=viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        // observing users data for updating view according to that.
        viewModel.userLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.rvUserList.adapter = UsersAdapter(this).apply {
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

        viewModel.eventStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UserViewEvent.ShowMessage -> {}
                is UserViewEvent.NavigateToDetail -> {}
            }
        }

    }

    override fun onUserClick(post: UserDTO) {

    }

}