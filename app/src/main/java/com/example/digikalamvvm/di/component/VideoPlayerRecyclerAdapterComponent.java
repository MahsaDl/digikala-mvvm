package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.ProductAdapterModule;
import com.example.digikalamvvm.di.module.VideoPlayerRecyclerAdapterModule;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.VideoPlayerRecyclerAdapter;

import dagger.Component;

@Component(modules = VideoPlayerRecyclerAdapterModule.class)
public interface VideoPlayerRecyclerAdapterComponent {
    VideoPlayerRecyclerAdapter getVideoPlayerRecyclerAdapter();

}
