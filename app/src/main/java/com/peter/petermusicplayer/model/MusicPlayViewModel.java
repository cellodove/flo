package com.peter.petermusicplayer.model;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.repository.Repository;
import com.peter.petermusicplayer.model.retrofit.APIService;
import com.peter.petermusicplayer.model.retrofit.RetrofitClient;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicPlayViewModel extends Observable {
    private Context context;
    private MusicInformation musicInformation;
    private Retrofit retrofit;

    public ObservableField<String> musicTitle;
    public ObservableField<String> albumName;
    public ObservableField<String> signer;
    public ObservableField<String> lyrics;

    public MusicPlayViewModel(Context context){
        System.out.println("뷰모델 동작하나");
        this.context = context;
        initialize();

    }

    private void initialize(){
        musicTitle = new ObservableField<>("너의의미");
        albumName = new ObservableField<>("꽃갈피");
        signer = new ObservableField<>("아이유");
        lyrics = new ObservableField<>("너의 그 한마디말도");

        RetrofitClient.getRetrofitClient().create(APIService.class).getPost("song.json").enqueue(new Callback<MusicInformation>() {
            @Override
            public void onResponse(Call<MusicInformation> call, Response<MusicInformation> response) {
                System.out.println("연결성공");
                musicInformation = response.body();
                System.out.println("musicInformation 데이터가 들어가지나"+musicInformation);
                musicTitle.set(musicInformation.getTitle());
                albumName.set(musicInformation.getAlbum());
                signer.set(musicInformation.getSinger());
                lyrics.set(musicInformation.getLyrics());
            }

            @Override
            public void onFailure(Call<MusicInformation> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
