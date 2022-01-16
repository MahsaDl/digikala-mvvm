package com.example.digikalamvvm.di.module;


import android.app.Application;

import com.example.digikalamvvm.model.repository.ProductRepository;
import com.example.digikalamvvm.view.fragment.NotificationFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NotificationFragmentModule {

  @Provides
   public NotificationFragment provideNotificationFragment(){
       return new NotificationFragment();
   }
}
