package com.peter.petermusicplayer.model.data;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class MusicInformation {
    @SerializedName("singer")
    private String singer;

    @SerializedName("album")
    private String album;

    @SerializedName("title")
    private String title;

    @SerializedName("duration")
    private int duration;

    @SerializedName("image")
    private String image;

    @SerializedName("file")
    private String file;

    @SerializedName("lyrics")
    private String lyrics;

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    @NonNull
    @Override
    public String toString() {
        return "받은데이터들{"+"\n"+
                "singer="+singer+"\n"+
                "album="+album+"\n"+
                "title="+title+"\n"+
                "duration="+duration+"\n"+
                "image="+image+"\n"+
                "file="+file+"\n"+
                "lyrics="+lyrics+"\n"+"}";
    }
}
