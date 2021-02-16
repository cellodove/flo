package com.peter.petermusicplayer.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.peter.petermusicplayer.R;
import com.peter.petermusicplayer.databinding.ActivityMusicPlayBinding;

public class MusicPlayActivity extends Activity {
    private ActivityMusicPlayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_music_play);
    }
}