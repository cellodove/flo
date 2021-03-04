package com.peter.petermusicplayer.view;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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

import static android.widget.Toast.LENGTH_SHORT;

public class MusicPlayActivity extends AppCompatActivity implements Observer {


    private ActivityMusicPlayBinding binding;
    private MusicPlayViewModel musicPlayViewModel;
    private SimpleExoPlayer exoPlayer;
    private Glide glide;
    private long playerTime;
    private Long[] lyricsTimeMillisecond;
    private FragmentManager fragmentManager;
    private LyricFragment lyricFragment;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_music_play);
        musicPlayViewModel = new MusicPlayViewModel(MusicPlayActivity.this);
        binding.setMusicPlayViewModel(musicPlayViewModel);
        setupObserver(musicPlayViewModel);
        initPlayer();
        initGlide();
        fragmentManager = getSupportFragmentManager();
        lyricFragment = new LyricFragment();
        lyricFragment.setArguments(getIntent().getExtras());
        getFragmentManager().beginTransaction().add(R.id.fullLyric,lyricFragment).commit();

        binding.lyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MusicPlayActivity.this,"lyric fragment", LENGTH_SHORT).show();
                binding.fullLyric.setVisibility(View.VISIBLE);

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
            showLyric();
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

    private void showLyric(){
        musicPlayViewModel.listLiveData.observe(this,list -> {
            lyricsTimeMillisecond = new Long[list.size()+1];
            List<String> lyricsTime = new ArrayList<>();
            List<String> lyrics = new ArrayList<>();
            String[] lyricsMillisecond;

            for (int i=0; i<list.size(); i++){
                lyricsTime.add(list.get(i).toString().substring(1,6));
                lyrics.add(list.get(i).toString().substring(11));
                lyricsMillisecond = lyricsTime.get(i).split(":");
                lyricsTimeMillisecond[i] = (Long.valueOf(lyricsMillisecond[0])*60000)+(Long.valueOf(lyricsMillisecond[1])*1000);
                System.out.println("가사타이밍 "+lyricsTimeMillisecond[i]);
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    for (int i=0; i<list.size(); i++){
                        Log.d(" getCurrentPosition()", String.valueOf(exoPlayer.getCurrentPosition()));
                        Log.d(" lyricsTimeMillisecond", String.valueOf(lyricsTimeMillisecond[i]));
                        try {
                            if (lyricsTimeMillisecond[i+1]==null){
                                lyricsTimeMillisecond[i+1]=lyricsTimeMillisecond[i]+1000;
                                lyrics.add(i+1,"");
                            }else {
                                if ((lyricsTimeMillisecond[i]>=exoPlayer.getCurrentPosition())&&(exoPlayer.getCurrentPosition()<lyricsTimeMillisecond[i+1])){
                                    binding.lyric1.setText(lyrics.get(i));
                                    binding.lyric2.setText(lyrics.get(i+1));
                                    break;
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    handler.postDelayed(this,1000);
                }
            },1000);
        });
    }

    @Override
    public void update(Observable o, Object arg) {}

    private void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

}