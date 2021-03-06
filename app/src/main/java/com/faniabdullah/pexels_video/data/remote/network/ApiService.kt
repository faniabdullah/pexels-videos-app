package com.faniabdullah.pexels_video.data.remote.network

import com.faniabdullah.pexels_video.data.remote.response.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("videos/popular?per_page=30")
    @Headers(
        "Authorization:Your Api Key"
    )
    fun getVideosPopular(): Flowable<Response>

    @GET("videos/search?per_page=30")
    @Headers(
        "Authorization:Your Api Key"
    )
    fun searchVideos(
        @Query("query") search: String
    ): Flowable<Response>
}