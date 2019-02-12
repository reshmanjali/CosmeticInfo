package com.example.reshmaanjali.cosmeticinfo;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoActivity extends YouTubeBaseActivity {
    public static final String my_api_key = BuildConfig.API_KEY;
    @BindView(R.id.id_youtube_video_view)
    YouTubePlayerView mYouTubeView;
    YouTubePlayer.OnInitializedListener mlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        mlistener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Toast.makeText(VideoActivity.this, R.string.you, Toast.LENGTH_SHORT).show();
                String s = getIntent().getStringExtra("youTube_key");
                youTubePlayer.loadVideo(s);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };
        mYouTubeView.initialize(my_api_key, mlistener);
    }
}
