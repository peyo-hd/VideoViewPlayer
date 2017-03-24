package com.peyo.videoview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class ExoPlayActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo);
        mExoView = (SimpleExoPlayerView) findViewById(R.id.exo_view);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        playVideoExo();
    }

    @Override
    protected void onPause() {
        releasePlayer();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        releasePlayer();
        super.onDestroy();
    }

    private SimpleExoPlayerView mExoView;
    private SimpleExoPlayer mPlayer;

    private String URL1 = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4";

    private void playVideoExo() {
        mPlayer = ExoPlayerFactory.newSimpleInstance(this,
                new DefaultTrackSelector(null),
                new DefaultLoadControl(), null);
        mExoView.setPlayer(mPlayer);
        mPlayer.prepare(new ExtractorMediaSource(Uri.parse(URL1),
                new DefaultDataSourceFactory(this,"ExoPlayActivity"),
                new DefaultExtractorsFactory(), null, null));
        mPlayer.setPlayWhenReady(true);
    }

    private void releasePlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
