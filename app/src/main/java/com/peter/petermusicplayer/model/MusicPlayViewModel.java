package com.peter.petermusicplayer.model;

import androidx.lifecycle.ViewModel;

import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.repository.Repository;

public class MusicPlayViewModel extends ViewModel {
    Repository repository;
    public MusicPlayViewModel(){
        repository = new Repository();
    }

    public MusicInformation getMusicInformation(){
        System.out.println("뷰모델 동작하나");
        return repository.getMusic();
    }

}
