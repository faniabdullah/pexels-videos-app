package com.faniabdullah.pexels_video.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.faniabdullah.pexels_video.R
import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.databinding.ItemVideoBinding

class Adapter : RecyclerView.Adapter<Adapter.ListViewHolder>() {
    private var data = ArrayList<VideosEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(videos: List<VideosEntity>) {
        data.clear()
        data.addAll(videos)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemVideoBinding.bind(itemView)
        fun bind(data: VideosEntity) {
            with(binding) {
                user.text = data.user?.name
                Glide.with(itemView.context)
                    .load(data.picturesItem)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .placeholder(R.drawable.ic_notifications_black_24dp)
                    .into(imageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Adapter.ListViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = data.size

}