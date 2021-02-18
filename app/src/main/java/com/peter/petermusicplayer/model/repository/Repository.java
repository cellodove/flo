package com.peter.petermusicplayer.model.repository;

import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.retrofit.RetrofitClient;

public class Repository {
    private static Repository instance;

    public Repository(){
    }

    public static Repository getInstance(){
        System.out.println("Repository getInstance 동작");
        if (instance == null){
            instance = new Repository();
        }
        return instance;
    }

    public MusicInformation getMusic(){
        RetrofitClient retrofitClient = new RetrofitClient();
        System.out.println("레포지토리 동작하나");
        return retrofitClient.getMusic();
    }
}
