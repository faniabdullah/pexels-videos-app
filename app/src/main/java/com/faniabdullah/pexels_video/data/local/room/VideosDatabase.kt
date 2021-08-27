package com.faniabdullah.pexels_video.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.faniabdullah.pexels_video.data.local.entity.VideosEntity

@Database(version = 1, exportSchema = false, entities = [VideosEntity::class])
abstract class VideosDatabase : RoomDatabase() {
    abstract fun videosDao(): VideosDao
}