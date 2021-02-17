package com.peter.petermusicplayer.model;

import androidx.lifecycle.ViewModel;

import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.repository.Repository;

public class MusicPlayViewModel extends ViewModel {

    public MusicInformation getMusicInformation(){
        Repository repository = new Repository();
        return repository.getMusic();
    }

}
