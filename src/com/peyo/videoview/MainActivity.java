package com.peyo.videoview;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
	private VideoView mVideo1;
	private VideoView mVideo2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mVideo1 = (VideoView) findViewById(R.id.video1);
		mVideo1.setMediaController(new MediaController(this));
	}



	@Override
	protected void onResume() {
		super.onResume();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
		mVideo1.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202012_%20Year%20In%20Review.mp4"));
		mVideo1.start();
	}
}
