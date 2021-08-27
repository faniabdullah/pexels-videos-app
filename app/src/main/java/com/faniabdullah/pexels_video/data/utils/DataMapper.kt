package com.faniabdullah.pexels_video.data.utils

import com.faniabdullah.pexels_video.data.local.entity.VideoFileUser
import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.data.remote.response.VideosItem

object DataMapper {
    fun responseToVideoEntity(listVideosItem: List<VideosItem>): List<VideosEntity> {
        val resultList = mutableListOf<VideosEntity>()
        val dataVideos = mutableListOf<VideoFileUser>()
        listVideosItem.forEach { response ->
            var counter = 0
            response.videoFiles?.forEach { videos ->
                dataVideos.add(
                    VideoFileUser(
                        fileType = videos.fileType,
                        width = videos.width,
                        link = videos.link,
                        id = videos.id,
                        quality = videos.quality,
                        height = videos.height,
                        picturesItem = response.videoPictures?.get(counter)?.picture
                    )
                )
                counter++
            }

            resultList.add(
                VideosEntity(
                    response.user,
                    dataVideos
                )
            )
        }

        return resultList
    }
}