package com.faniabdullah.pexels_video.data.remote.response

import com.google.gson.annotations.SerializedName

data class Response(

    @field:SerializedName("next_page")
    val nextPage: String? = null,

    @field:SerializedName("per_page")
    val perPage: Int? = null,

    @field:SerializedName("videos")
    val videos: List<VideosItem>? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)

data class User(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("url")
    val url: String? = null
)

data class VideoPicturesItem(

    @field:SerializedName("nr")
    val nr: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("picture")
    val picture: String? = null
)

data class VideosItem(

    @field:SerializedName("duration")
    val duration: Int? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("avg_color")
    val avgColor: Any? = null,

    @field:SerializedName("video_files")
    val videoFiles: List<VideoFilesItem>? = null,

    @field:SerializedName("video_pictures")
    val videoPictures: List<VideoPicturesItem>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("full_res")
    val fullRes: Any? = null,

    @field:SerializedName("user")
    val user: User? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("height")
    val height: Int? = null,

    @field:SerializedName("tags")
    val tags: List<Any?>? = null
)

data class VideoFilesItem(

    @field:SerializedName("file_type")
    val fileType: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("link")
    val link: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("quality")
    val quality: String? = null,

    @field:SerializedName("height")
    val height: Int? = null
)
