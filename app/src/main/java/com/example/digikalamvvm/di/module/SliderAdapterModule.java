package com.example.digikalamvvm.di.module;

import android.content.Context;

import com.example.digikalamvvm.view.adapter.SliderAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class SliderAdapterModule {
    private Context context;

    public SliderAdapterModule(Context context) {
        this.context = context;
    }

    @Provides
    public SliderAdapter sliderAdapter(){
        return new SliderAdapter(context);
    }
}
