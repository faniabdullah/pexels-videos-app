package com.faniabdullah.pexels_video.di

import com.faniabdullah.pexels_video.data.IVideosRepository
import com.faniabdullah.pexels_video.data.RepositoryVideos
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {
    @Binds
    abstract fun ProvideVideosRepository(repositoryVideos: RepositoryVideos): IVideosRepository
}