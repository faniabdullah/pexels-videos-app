package com.faniabdullah.pexels_video.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
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
                user.text = data.user
                val height = if (data.height ?: 0 > 300) 300 else data.height ?: 0
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(data.picturesItem)
                    .override(
                        data.width ?: 500, height
                    )
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            imageView.setImageBitmap(resource)
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            // this is called when imageView is cleared on lifecycle call or for
                            // some other reason.
                            // if you are referencing the bitmap somewhere else too other than this imageView
                            // clear it here as you can no longer have the bitmap
                        }
                    })
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