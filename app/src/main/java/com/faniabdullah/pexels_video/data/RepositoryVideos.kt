package com.faniabdullah.pexels_video.data

import com.faniabdullah.pexels_video.data.local.LocalDataSource
import com.faniabdullah.pexels_video.data.remote.RemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepositoryVideos @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IVideosRepository {


}