package com.peter.petermusicplayer.model.repository;

import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.retrofit.APIService;
import com.peter.petermusicplayer.model.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private RetrofitClient retrofitClient = new RetrofitClient();

    public static Repository getInstance(){
        return LazyHolder.REPOSITORY;
    }

    private static class LazyHolder{
        private static final Repository REPOSITORY = new Repository();
    }


    public void getMusic(){
        RetrofitClient.getRetrofitClient().create(APIService.class).getPost("song.json").enqueue(new Callback<MusicInformation>() {
            @Override
            public void onResponse(Call<MusicInformation> call, Response<MusicInformation> response) {
                System.out.println("연결성공");
                MusicInformation musicInformation = response.body();
            }
            @Override
            public void onFailure(Call<MusicInformation> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
