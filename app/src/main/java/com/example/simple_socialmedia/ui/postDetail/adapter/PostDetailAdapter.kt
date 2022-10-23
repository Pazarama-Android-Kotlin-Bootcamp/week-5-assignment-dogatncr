package com.example.simple_socialmedia.ui.postDetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_socialmedia.data.model.Comment
import com.simple_socialmedia.databinding.ItemCommentLayoutBinding


//Post Detail adapter for showing comments in Recycler View
class PostDetailAdapter(private val comments: List<Comment>) : RecyclerView.Adapter<PostDetailAdapter.PostDetailViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostDetailViewHolder {
       return PostDetailViewHolder(
           ItemCommentLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostDetailViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    class PostDetailViewHolder(private val binding: ItemCommentLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.dataHolder = comment
            binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
       return comments.size
    }
}

