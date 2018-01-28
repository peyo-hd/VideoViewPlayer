package com.peyo.videoview

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import kotlinx.android.synthetic.main.main.*

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        videoView.setMediaController(MediaController(this))
    }

    private val URL = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4"

    override fun onResume() {
        super.onResume()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        videoView.setVideoURI(Uri.parse(URL))
        videoView.start()
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
