package com.peter.petermusicplayer.model.repository;

import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.retrofit.RetrofitClient;

public class Repository {

    private RetrofitClient retrofitClient = new RetrofitClient();

    public static Repository getInstance(){
        return LazyHolder.REPOSITORY;
    }
    private static class LazyHolder{
        private static final Repository REPOSITORY = new Repository();
    }


    public MusicInformation getMusic(){
        System.out.println("레포지토리 동작하나");
        return retrofitClient.getMusic();
    }
}
