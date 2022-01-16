package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.ProductAdapterModule;
import com.example.digikalamvvm.di.module.VideoAdapterModule;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.VideoAdapter;

import dagger.Component;

@Component(modules = VideoAdapterModule.class)
public interface VideoAdapterComponent {
    VideoAdapter getVideoAdapter();

}
