package com.faniabdullah.pexels_video.data.utils

import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.data.remote.response.VideosItem

object DataMapper {
    fun responseToVideoEntity(listVideosItem: List<VideosItem>): List<VideosEntity> {
        val resultList = mutableListOf<VideosEntity>()
        listVideosItem.forEach { response ->
            val videos = response.videoFiles?.get(0)
            resultList.add(
                VideosEntity(
                    user = response.user?.name.toString(),
                    fileType = videos?.fileType,
                    width = videos?.width,
                    link = videos?.link,
                    id = videos?.id,
                    quality = videos?.quality,
                    height = videos?.height,
                    picturesItem = response.image
                )
            )
        }

        return resultList
    }
}