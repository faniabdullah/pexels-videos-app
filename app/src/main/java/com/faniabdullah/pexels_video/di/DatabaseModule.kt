package com.faniabdullah.pexels_video.di

import android.content.Context
import androidx.room.Room
import com.faniabdullah.pexels_video.data.local.room.VideosDao
import com.faniabdullah.pexels_video.data.local.room.VideosDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideVideosDatabase(@ApplicationContext context: Context): VideosDatabase {
        return Room.databaseBuilder(
            context,
            VideosDatabase::class.java, "Videos.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    fun videosDao(database: VideosDatabase): VideosDao = database.videosDao()
}









