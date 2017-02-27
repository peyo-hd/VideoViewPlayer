package com.peyo.videoview;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.view.View;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class ExoPlayActivity extends Activity implements Callback {
    private SurfaceView mSurface1, mSurface2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo);
        mSurface1 = (SurfaceView) findViewById(R.id.surface1);
        mSurface1.getHolder().addCallback(this);
        mSurface2 = (SurfaceView) findViewById(R.id.surface2);
        mSurface2.getHolder().addCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
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

    @Override
    public void surfaceCreated(SurfaceHolder holder) { playVideo(holder); }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {}


    private SimpleExoPlayer mPlayer1;
    private MediaPlayer mPlayer2;
    private boolean first = true;

    private String URL1 = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4";
    private String URL2 = "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202012_%20Year%20In%20Review.mp4";

    private void playVideo(SurfaceHolder holder) {
        if (first) {
            playVideoExo(holder);
            first = false;
        } else {
            mPlayer2 = new MediaPlayer();
            try {
                mPlayer2.setDisplay(holder);
                mPlayer2.setDataSource(URL2);
                mPlayer2.prepare();
                mPlayer2.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void playVideoExo(SurfaceHolder holder) {
        mPlayer1 = ExoPlayerFactory.newSimpleInstance(this,
                new DefaultTrackSelector(null),
                new DefaultLoadControl(), null);
        mPlayer1.setVideoSurfaceHolder(holder);
        mPlayer1.prepare(new ExtractorMediaSource(Uri.parse(URL1),
                new DefaultDataSourceFactory(this,"ExoPlayActivity"),
                new DefaultExtractorsFactory(), null, null));
        mPlayer1.setPlayWhenReady(true);
    }

    private void releasePlayer() {
        if (mPlayer1 != null) {
            mPlayer1.release();
            mPlayer1 = null;
        }
        if (mPlayer2 != null) {
            mPlayer2.release();
            mPlayer2 = null;
        }
    }
}
