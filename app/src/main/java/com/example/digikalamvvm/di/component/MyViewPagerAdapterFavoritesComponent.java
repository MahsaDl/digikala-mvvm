package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.MyViewPagerAdapterFavoritesModule;
import com.example.digikalamvvm.di.module.MyViewPagerAdapterModule;
import com.example.digikalamvvm.view.MyViewPagerAdapter;
import com.example.digikalamvvm.view.adapter.MyViewPagerAdapterFavorites;

import dagger.Component;

@Component(modules = MyViewPagerAdapterFavoritesModule.class)
public interface MyViewPagerAdapterFavoritesComponent {
    MyViewPagerAdapterFavorites getMyViewPagerAdapterFavorites();

}
