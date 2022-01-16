package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.SliderAdapterModule;
import com.example.digikalamvvm.view.adapter.SliderAdapter;

import dagger.Component;

@Component(modules = SliderAdapterModule.class)
public interface SliderAdapterComponent {

    SliderAdapter getSliderAdapter();
    //void inject(HomeFragment homeFragment);
}
