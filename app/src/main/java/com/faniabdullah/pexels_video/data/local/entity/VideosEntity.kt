package com.faniabdullah.pexels_video.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class VideosEntity(
    val user: String,
    val fileType: String? = null,
    val width: Int? = null,
    val link: String? = null,
    @PrimaryKey
    val id: Int? = null,
    val quality: String? = null,
    val height: Int? = null,
    val picturesItem: String? = null
)