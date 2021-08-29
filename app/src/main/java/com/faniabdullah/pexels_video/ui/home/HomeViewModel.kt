package com.faniabdullah.pexels_video.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.faniabdullah.pexels_video.data.RepositoryVideos
import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.data.utils.Resource

class HomeViewModel @ViewModelInject constructor(private val repositoryVideos: RepositoryVideos) :
    ViewModel() {
    val videos = LiveDataReactiveStreams.fromPublisher(repositoryVideos.getPopularVideos())
    fun searchVideos(string: String): LiveData<Resource<List<VideosEntity>>> {
        return LiveDataReactiveStreams.fromPublisher(repositoryVideos.getSearchVideos(string))
    }
}