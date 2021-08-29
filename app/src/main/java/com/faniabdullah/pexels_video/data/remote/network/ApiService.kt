package com.faniabdullah.pexels_video.data.remote.network

import com.faniabdullah.pexels_video.data.remote.response.Response
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("videos/popular?per_page=30")
    @Headers(
        "Authorization:563492ad6f91700001000001000f13d7d5a24c58a244c7a53de05f52"
    )
    fun getVideosPopular(): Flowable<Response>

    @GET("videos/search?per_page=30")
    @Headers(
        "Authorization:563492ad6f91700001000001000f13d7d5a24c58a244c7a53de05f52"
    )
    fun searchVideos(
        @Query("query") search: String
    ): Flowable<Response>
}