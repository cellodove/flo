package com.peter.petermusicplayer.model.retrofit;

import com.peter.petermusicplayer.model.data.MusicInformation;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("2020-flo/{post}")
    Call<MusicInformation> getPost(@Path("post") String post);
}
