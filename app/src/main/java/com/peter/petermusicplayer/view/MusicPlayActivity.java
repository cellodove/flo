package com.peter.petermusicplayer.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.peter.petermusicplayer.R;
import com.peter.petermusicplayer.databinding.ActivityMusicPlayBinding;
import com.peter.petermusicplayer.model.MusicPlayViewModel;
import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.repository.Repository;

public class MusicPlayActivity extends Activity {
    private ActivityMusicPlayBinding binding;
    private MusicPlayViewModel musicPlayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_music_play);
        musicPlayViewModel = new MusicPlayViewModel();
        musicPlayViewModel.getMusicInformation();
        System.out.println("액티비티까지 데이터가 들어와지는가 노래제목:"+musicPlayViewModel.getMusicInformation());
    }
}