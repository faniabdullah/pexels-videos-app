package com.faniabdullah.pexels_video.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.faniabdullah.pexels_video.data.RepositoryVideos

class HomeViewModel @ViewModelInject constructor(private val repositoryVideos: RepositoryVideos) :
    ViewModel() {
    val videos = LiveDataReactiveStreams.fromPublisher(repositoryVideos.getPopularVideos())
}