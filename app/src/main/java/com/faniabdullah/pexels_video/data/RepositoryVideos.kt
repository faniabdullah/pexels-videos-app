package com.faniabdullah.pexels_video.data

import android.annotation.SuppressLint
import com.faniabdullah.pexels_video.data.local.LocalDataSource
import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.data.remote.ApiResponse
import com.faniabdullah.pexels_video.data.remote.RemoteDataSource
import com.faniabdullah.pexels_video.data.utils.DataMapper
import com.faniabdullah.pexels_video.data.utils.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RepositoryVideos @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IVideosRepository {
    @SuppressLint("CheckResult")
    override fun getPopularVideos(): Flowable<Resource<List<VideosEntity>>> {
        val data = remoteDataSource.getListVideoPopular()
        val compositeDisposable = CompositeDisposable()
        val result = PublishSubject.create<Resource<List<VideosEntity>>>()
        result.onNext(Resource.Loading())
        val responseRemote = data
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.clear()
            }
            .subscribe({ response ->
                result.onNext(Resource.Loading())
                when (response) {
                    is ApiResponse.Success -> {
                        val videosEntity = DataMapper.responseToVideoEntity(response.data)
                        result.onNext(Resource.Success(videosEntity))
                    }
                    is ApiResponse.Empty -> {
                        result.onNext(Resource.Error("data empty", null))
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage, null))
                    }
                }

            }, {
                result.onNext(Resource.Error(it.message.toString(), null))
            })

        compositeDisposable.add(responseRemote)

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }

    override fun getSearchVideos(s: String): Flowable<Resource<List<VideosEntity>>> {
        val data = remoteDataSource.getSearchVideo(s)
        val compositeDisposable = CompositeDisposable()
        val result = PublishSubject.create<Resource<List<VideosEntity>>>()
        result.onNext(Resource.Loading())
        val responseRemote = data
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .doOnComplete {
                compositeDisposable.clear()
            }
            .subscribe({ response ->
                result.onNext(Resource.Loading())
                when (response) {
                    is ApiResponse.Success -> {
                        val videosEntity = DataMapper.responseToVideoEntity(response.data)
                        result.onNext(Resource.Success(videosEntity))
                    }
                    is ApiResponse.Empty -> {
                        result.onNext(Resource.Error("data empty", null))
                    }
                    is ApiResponse.Error -> {
                        result.onNext(Resource.Error(response.errorMessage, null))
                    }
                }

            }, {
                result.onNext(Resource.Error(it.message.toString(), null))
            })

        compositeDisposable.add(responseRemote)

        return result.toFlowable(BackpressureStrategy.BUFFER)
    }
}