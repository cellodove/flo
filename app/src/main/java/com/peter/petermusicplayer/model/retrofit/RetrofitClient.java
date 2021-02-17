package com.peter.petermusicplayer.model.retrofit;

import com.peter.petermusicplayer.model.data.MusicInformation;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://grepp-programmers-challenges.s3.ap-northeast-2.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public APIService apiService = retrofit.create(APIService.class);

    public Call<MusicInformation> call = apiService.getPost("song.json");

    public MusicInformation getMusic(){
        final MusicInformation[] musicInformation = new MusicInformation[1];

        call.enqueue(new Callback<MusicInformation>() {
            @Override
            public void onResponse(Call<MusicInformation> call, Response<MusicInformation> response) {
                if (response.isSuccessful()){
                    musicInformation[0] = response.body();
                    System.out.printf(musicInformation[0].toString());
                }
            }
            @Override
            public void onFailure(Call<MusicInformation> call, Throwable t) {
                System.out.printf("연결실패");
                t.printStackTrace();
            }
        });
        return musicInformation[0];
    }

}
