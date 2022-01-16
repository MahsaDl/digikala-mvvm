package com.example.digikalamvvm.di.module;

import com.example.digikalamvvm.model.api.ProductApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {
    @Provides
    public Retrofit retrofitProvide(){
        return new Retrofit.Builder()
                .baseUrl(ProductApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
