package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.RetrofitModule;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    Retrofit getRetrofit();
}
