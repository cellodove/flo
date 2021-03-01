package com.peter.petermusicplayer.view;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.offline.ProgressiveDownloadAction;
import com.google.android.exoplayer2.offline.ProgressiveDownloader;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.peter.petermusicplayer.R;
import com.peter.petermusicplayer.databinding.ActivityMusicPlayBinding;
import com.peter.petermusicplayer.model.MusicPlayViewModel;
import com.peter.petermusicplayer.model.data.MusicInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MusicPlayActivity extends AppCompatActivity implements Observer {


    private ActivityMusicPlayBinding binding;
    private MusicPlayViewModel musicPlayViewModel;
    private SimpleExoPlayer exoPlayer;
    private Glide glide;
    private long playerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_music_play);
        musicPlayViewModel = new MusicPlayViewModel(MusicPlayActivity.this);
        binding.setMusicPlayViewModel(musicPlayViewModel);
        setupObserver(musicPlayViewModel);
        initPlayer();
        initGlide();

        binding.lyrics.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    private void initPlayer() {
        musicPlayViewModel.liveData.observe(this,musicInformation ->
        {
            if (exoPlayer == null){
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this);
            binding.exoPlayer.setPlayer(exoPlayer);
            binding.exoPlayer.setShowTimeoutMs(0);
            Uri uri = Uri.parse(musicInformation.getFile());
            DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(musicInformation.getFile());
            MediaSource mediaSource = new ExtractorMediaSource.Factory(defaultHttpDataSourceFactory)
                    .createMediaSource(uri);
            exoPlayer.prepare(mediaSource);
            /*showLyric();*/
            /*exoPlayer.addListener(new Player.EventListener() {
                @Override
                public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                    if (playbackState == Player.)
                }
            });*/
            }
        });
    }

    private void initGlide(){
        musicPlayViewModel.liveData.observe(this,musicInformation -> {
            glide.with(this)
                    .load(musicInformation.getImage())
                    .override(200,200)
                    .into(binding.songImage);
        });

    }

/*    private void showLyric(){
        musicPlayViewModel.listLiveData.observe(this,list -> {
            List<String> lyricsTime = new ArrayList<>();
            List<String> lyrics = new ArrayList<>();
            for (int i=0; i<list.size(); i++){
                lyricsTime.add(list.get(i).toString().substring(1,6));
                lyrics.add(list.get(i).toString().substring(11));
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.d("tag", String.valueOf(exoPlayer.getCurrentPosition()));
                    handler.postDelayed(this,1000);
                }
            },1000);
        });
    }*/

    @Override
    public void update(Observable o, Object arg) {}

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

}