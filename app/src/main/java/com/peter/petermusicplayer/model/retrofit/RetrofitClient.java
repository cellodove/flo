package com.peter.petermusicplayer.model.retrofit;

import com.peter.petermusicplayer.model.data.MusicInformation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    MusicInformation musicInformation;

    public RetrofitClient(){
    }


    public MusicInformation getMusic(){
        return musicInformation;
    }

    public static Retrofit getRetrofitClient(){
        return new Retrofit.Builder()
                .baseUrl("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



}
