package com.example.simple_socialmedia.ui.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_socialmedia.data.model.PostDTO
import com.simple_socialmedia.databinding.ItemPostLayoutBinding


/**
 * * 8.10.2022.
 */

class PostsAdapter(private val listener: OnPostClickListener) : ListAdapter<PostDTO, PostsAdapter.PostViewHolder>(
    PostsDiffUtil()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
       return PostViewHolder(
            ItemPostLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class PostViewHolder(private val binding: ItemPostLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostDTO, listener: OnPostClickListener) {
            binding.postCard.setOnClickListener{
                listener.onPostClick(post)
            }
            binding.dataHolder = post
            binding.ivPostImage.setOnClickListener {
                listener.onLikeClick(post)
            }
            binding.executePendingBindings()
        }
    }

    class PostsDiffUtil : DiffUtil.ItemCallback<PostDTO>() {
        override fun areItemsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem.postId == newItem.postId
        }

        override fun areContentsTheSame(oldItem: PostDTO, newItem: PostDTO): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnPostClickListener {
    fun onPostClick(post: PostDTO)
    fun onLikeClick(post: PostDTO)
}

