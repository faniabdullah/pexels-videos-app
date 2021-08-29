package com.faniabdullah.pexels_video.data

import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.data.utils.Resource
import io.reactivex.Flowable

interface IVideosRepository {
    fun getPopularVideos(): Flowable<Resource<List<VideosEntity>>>
    fun getSearchVideos(s : String): Flowable<Resource<List<VideosEntity>>>
}