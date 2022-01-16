package com.example.digikalamvvm.di.module;

import com.bumptech.glide.RequestManager;
import com.example.digikalamvvm.model.room.entity.MediaObject;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.VideoPlayerRecyclerAdapter;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoPlayerRecyclerAdapterModule {
    private ArrayList<MediaObject> list;
    private RequestManager requestManager;

    public VideoPlayerRecyclerAdapterModule(ArrayList<MediaObject> list, RequestManager requestManager) {
        this.list = list;
        this.requestManager = requestManager;
    }

    @Provides
    public VideoPlayerRecyclerAdapter videoPlayerRecyclerAdapter(){
        return new VideoPlayerRecyclerAdapter(list,requestManager);
    }
}
