package com.example.digikalamvvm.di.module;

import com.example.digikalamvvm.model.room.entity.Video;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.VideoAdapter;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class VideoAdapterModule {
    private List<Video> list;

    public VideoAdapterModule(List<Video> list) {
        this.list = list;
    }

    @Provides
    public VideoAdapter videoAdapter(){
        return new VideoAdapter(list);
    }
}
