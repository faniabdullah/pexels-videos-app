package com.faniabdullah.pexels_video.data.local.entity

import com.faniabdullah.pexels_video.data.remote.response.User
import com.faniabdullah.pexels_video.data.remote.response.VideoFilesItem

data class VideosEntity(
    val user: List<User>,
    val videosUser: VideoFilesItem
)

data class VideoFileUser(
    val fileType: String? = null,
    val width: Int? = null,
    val link: String? = null,
    val id: Int? = null,
    val quality: String? = null,
    val height: Int? = null,
    val picturesItem: String? = null
)