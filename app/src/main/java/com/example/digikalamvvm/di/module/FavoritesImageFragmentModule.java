package com.example.digikalamvvm.di.module;


import com.example.digikalamvvm.view.fragment.FavoritesImageFragment;
import com.example.digikalamvvm.view.fragment.NotificationFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class FavoritesImageFragmentModule {

  @Provides
   public FavoritesImageFragment provideFavoritesImageFragment(){
       return new FavoritesImageFragment();
   }
}
