package com.faniabdullah.pexels_video.data.local.entity

import com.faniabdullah.pexels_video.data.remote.response.User

data class VideosEntity(
    val user: User? = null,
    val fileType: String? = null,
    val width: Int? = null,
    val link: String? = null,
    val id: Int? = null,
    val quality: String? = null,
    val height: Int? = null,
    val picturesItem: String? = null
)