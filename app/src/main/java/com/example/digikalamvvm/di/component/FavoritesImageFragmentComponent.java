package com.example.digikalamvvm.di.component;


import com.example.digikalamvvm.di.module.FavoritesImageFragmentModule;
import com.example.digikalamvvm.di.module.NotificationFragmentModule;
import com.example.digikalamvvm.view.fragment.FavoritesImageFragment;
import com.example.digikalamvvm.view.fragment.NotificationFragment;

import dagger.Component;

@Component(modules = FavoritesImageFragmentModule.class)
public interface FavoritesImageFragmentComponent {
    FavoritesImageFragment getFavoritesImageFragment();
}
