package com.peter.petermusicplayer.model;

import android.content.Context;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.gson.Gson;
import com.peter.petermusicplayer.model.data.MusicInformation;
import com.peter.petermusicplayer.model.repository.Repository;
import com.peter.petermusicplayer.model.retrofit.APIService;
import com.peter.petermusicplayer.model.retrofit.RetrofitClient;
import com.peter.petermusicplayer.view.MusicPlayActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicPlayViewModel extends Observable {
    private Context context;
    private MusicInformation musicInformation;
    private MutableLiveData<MusicInformation> _mutableLiveData = new MutableLiveData<>();
    public LiveData<MusicInformation> liveData = _mutableLiveData;

    private MutableLiveData<List> _mutableListLiveData = new MutableLiveData<>();
    public LiveData<List> listLiveData = _mutableListLiveData;

    public ObservableField<String> musicTitle;
    public ObservableField<String> albumName;
    public ObservableField<String> signer;
    public ObservableField<String> lyric;
    public List<String> lyricsList = new ArrayList<>();
    List<String> lyrics = new ArrayList<>();

    public MusicPlayViewModel(Context context){
        System.out.println("뷰모델 동작하나");
        this.context = context;
        initialize();
    }

    private void initialize(){
        Repository.getInstance();
        musicTitle = new ObservableField<>("너의의미");
        albumName = new ObservableField<>("꽃갈피");
        signer = new ObservableField<>("아이유");
        lyric = new ObservableField<>("");

        RetrofitClient.getRetrofitClient().create(APIService.class).getPost("song.json").enqueue(new Callback<MusicInformation>() {
            @Override
            public void onResponse(Call<MusicInformation> call, Response<MusicInformation> response) {
                System.out.println("연결성공");
                musicInformation = response.body();
                _mutableLiveData.setValue(response.body());
                musicTitle.set(musicInformation.getTitle());
                albumName.set(musicInformation.getAlbum());
                signer.set(musicInformation.getSinger());
                lyricsList = Arrays.asList(musicInformation.getLyrics().split("\n"));
                for (int i=0; i<lyricsList.size(); i++){
                    lyrics.add(lyricsList.get(i).substring(11)+"\n");
                }
                lyric.set(lyrics.toString());
                _mutableListLiveData.setValue(lyricsList);
                System.out.println("리스크 크기 "+ lyricsList.size());
            }
            @Override
            public void onFailure(Call<MusicInformation> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
