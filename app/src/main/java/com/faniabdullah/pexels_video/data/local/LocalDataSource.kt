package com.faniabdullah.pexels_video.data.local

import com.faniabdullah.pexels_video.data.local.room.VideosDatabase
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val videosDatabase: VideosDatabase) {


}