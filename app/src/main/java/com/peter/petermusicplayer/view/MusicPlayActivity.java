package com.peter.petermusicplayer.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.peter.petermusicplayer.R;
import com.peter.petermusicplayer.databinding.ActivityMusicPlayBinding;
import com.peter.petermusicplayer.model.MusicPlayViewModel;

public class MusicPlayActivity extends AppCompatActivity {
    private ActivityMusicPlayBinding binding;
    private MusicPlayViewModel musicPlayViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_music_play);
        musicPlayViewModel = new ViewModelProvider(this).get(MusicPlayViewModel.class);

        musicPlayViewModel.getMusicInformation();
        System.out.println("액티비티에 들어가지는가 "+musicPlayViewModel.getMusicInformation());

    }
}