package com.peyo.videoview

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.exo.*

class ExoPlayActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exo)
    }

    private val URL = "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4"

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val player = ExoPlayerFactory.newSimpleInstance(this)
        exoPlayerView.player = player

        player.prepare(ProgressiveMediaSource.Factory(
                DefaultDataSourceFactory(this, "ExoPlayActivity"))
                .createMediaSource(Uri.parse(URL)))
        player.playWhenReady = true
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
