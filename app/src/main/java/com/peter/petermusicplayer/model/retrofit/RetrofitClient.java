package com.peter.petermusicplayer.model.retrofit;

import com.peter.petermusicplayer.model.data.MusicInformation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    MusicInformation musicInformation;
    Retrofit retrofit;

    public RetrofitClient(){
    }

    public void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<MusicInformation> call = apiService.getPost("song.json");

        call.enqueue(new Callback<MusicInformation>() {
            @Override
            public void onResponse(Call<MusicInformation> call, Response<MusicInformation> response) {
                if (response.isSuccessful()){
                    System.out.println("연결성공");
                    musicInformation = response.body();
                    if(null!= musicInformation) getMusic();
                    System.out.println("musicInformation 데이터가 들어가지나"+musicInformation);
                }
            }
            @Override
            public void onFailure(Call<MusicInformation> call, Throwable t) {
                System.out.printf("연결실패");
                t.printStackTrace();
            }
        });

    }

    public MusicInformation getMusic(){
        System.out.println("성공한다음에 리턴하라고");
        initRetrofit();
        return musicInformation;

    }
}
