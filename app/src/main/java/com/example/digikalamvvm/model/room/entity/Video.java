package com.example.digikalamvvm.model.room.entity;

import android.content.Context;

import com.google.android.exoplayer2.SimpleExoPlayer;

public class Video {
    private String url;
    private Context context;
    private int imgProfile;
    String userName;
    String title;
    String views;
    String time;
    Boolean playWhenReady = false;
    int currentWindow = 0;
    long playbackPosition = 0;
    SimpleExoPlayer player;


    public Video(String url, Context context, String userName, String title, String views, String time) {
        this.url = url;
        this.context = context;
        this.userName=userName;
        this.title=title;
        this.views=views;
        this.time=time;

    }

    public Video(String url,Context context, int imgProfile, String userName, String title, String views, String time) {
        this.url = url;
        this.context = context;
        this.imgProfile = imgProfile;
        this.userName = userName;
        this.title = title;
        this.views = views;
        this.time = time;
    }

    public void setCurrentWindow(int currentWindow) {
        this.currentWindow = currentWindow;
    }

    public void setPlaybackPosition(long playbackPosition) {
        this.playbackPosition = playbackPosition;
    }

    public void setPlayWhenReady(Boolean playWhenReady) {
        this.playWhenReady = playWhenReady;
    }

    public void setPlayer(SimpleExoPlayer player) {
        this.player = player;
    }

    public SimpleExoPlayer getPlayer() {
        return player;
    }

    public String getUrl() {
        return url;
    }

    public Context getContext() {
        return context;
    }

    public Boolean getPlayWhenReady() {
        return playWhenReady;
    }

    public int getCurrentWindow() {
        return currentWindow;
    }

    public long getPlaybackPosition() {
        return playbackPosition;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImgProfile() {
        return imgProfile;
    }

    public void setImgProfile(int imgProfile) {
        this.imgProfile = imgProfile;
    }
}
