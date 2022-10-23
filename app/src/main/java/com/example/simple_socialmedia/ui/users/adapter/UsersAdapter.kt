package com.example.simple_socialmedia.ui.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_socialmedia.data.model.UserDTO
import com.simple_socialmedia.databinding.ItemUserLayoutBinding


/**
 * Adapter for User Recycler View
 */

class UsersAdapter(private val listener: OnUserClickListener) : ListAdapter<UserDTO, UsersAdapter.UserViewHolder>(
    UsersDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       return UserViewHolder(
            ItemUserLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class UserViewHolder(private val binding: ItemUserLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: UserDTO, listener: OnUserClickListener) {
            binding.dataHolder = post

            binding.executePendingBindings()
        }
    }

    class UsersDiffUtil : DiffUtil.ItemCallback<UserDTO>() {
        override fun areItemsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserDTO, newItem: UserDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnUserClickListener {
    fun onUserClick(post: UserDTO)
}
