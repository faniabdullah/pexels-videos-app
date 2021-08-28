package com.faniabdullah.pexels_video

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.faniabdullah.pexels_video.data.local.entity.VideosEntity
import com.faniabdullah.pexels_video.databinding.ActivityDetailVideosBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.util.Util

class DetailVideos : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition = 0L
    private var data: VideosEntity? = null

    companion object {
        const val KEY_DETAIL_VIDEOS = "key_detail_videos"
    }

    private val viewBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityDetailVideosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        data = intent.getParcelableExtra(KEY_DETAIL_VIDEOS)
    }

    private fun initializePlayer() {
        val link: String = data?.link.toString()
        player = SimpleExoPlayer.Builder(this)
            .build()
            .also { exoPlayer ->
                viewBinding.videoView.player = exoPlayer
                val mediaItem =
                    MediaItem.fromUri(link)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.playWhenReady = playWhenReady
                exoPlayer.seekTo(currentWindow, playbackPosition)
                exoPlayer.prepare()
            }
    }


    private fun releasePlayer() {
        player?.run {
            playbackPosition = this.currentPosition
            currentWindow = this.currentWindowIndex
            playWhenReady = this.playWhenReady
            release()
        }
        player = null
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        hideSystemUi()
        if ((Util.SDK_INT < 24 || player == null)) {
            initializePlayer()
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        viewBinding.videoView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}