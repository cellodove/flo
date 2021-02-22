package com.peter.petermusicplayer.view;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.offline.ProgressiveDownloadAction;
import com.google.android.exoplayer2.offline.ProgressiveDownloader;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.peter.petermusicplayer.R;
import com.peter.petermusicplayer.databinding.ActivityMusicPlayBinding;
import com.peter.petermusicplayer.model.MusicPlayViewModel;

import java.util.Observable;
import java.util.Observer;

public class MusicPlayActivity extends AppCompatActivity implements Observer {


    private ActivityMusicPlayBinding binding;
    private MusicPlayViewModel musicPlayViewModel;
    private SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_music_play);
        musicPlayViewModel = new MusicPlayViewModel(MusicPlayActivity.this);
        binding.setMusicPlayViewModel(musicPlayViewModel);
        setupObserver(musicPlayViewModel);
        initPlayer();

    }

    private void initPlayer() {
        if (exoPlayer == null){
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this);
            binding.exoPlayer.setPlayer(exoPlayer);
            binding.exoPlayer.setShowTimeoutMs(0);
            Uri uri = Uri.parse("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/2020-flo/music.mp3");
            DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/2020-flo/music.mp3");
            MediaSource mediaSource = new ExtractorMediaSource.Factory(defaultHttpDataSourceFactory)
                    .createMediaSource(uri);
            exoPlayer.prepare(mediaSource);
        }
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

}