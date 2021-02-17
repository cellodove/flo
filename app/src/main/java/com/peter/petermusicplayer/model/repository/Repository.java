package com.peter.petermusicplayer.model.repository;

import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.retrofit.RetrofitClient;

public class Repository {
    private static Repository instance;
    public Repository(){}

    public static Repository getInstance(){
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public MusicInformation getMusic(){
        RetrofitClient retrofitClient = new RetrofitClient();
        return retrofitClient.getMusic();
    }
}
