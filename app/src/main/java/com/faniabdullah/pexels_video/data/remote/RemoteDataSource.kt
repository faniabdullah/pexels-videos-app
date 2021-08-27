package com.faniabdullah.pexels_video.data.remote

import android.annotation.SuppressLint
import com.faniabdullah.pexels_video.data.remote.network.ApiService
import com.faniabdullah.pexels_video.data.remote.response.VideosItem
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}


class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    @SuppressLint("CheckResult")
    fun getListVideoPopular(): Flowable<ApiResponse<List<VideosItem>>> {
        val resultData = PublishSubject.create<ApiResponse<List<VideosItem>>>()
        val client = apiService.getVideosPopular()
        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataVideos = response.videos
                if (dataVideos != null) {
                    resultData.onNext(
                        if (dataVideos.isNotEmpty()) ApiResponse.Success(dataVideos) else ApiResponse.Empty
                    )
                }
            }, {
                resultData.onNext(
                    ApiResponse.Error(it.message.toString())
                )
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

}