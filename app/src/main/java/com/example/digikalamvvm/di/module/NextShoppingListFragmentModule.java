package com.example.digikalamvvm.di.module;


import com.example.digikalamvvm.view.fragment.NextShoppingListFragment;
import com.example.digikalamvvm.view.fragment.NotificationFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NextShoppingListFragmentModule {

  @Provides
   public NextShoppingListFragment provideNextShoppingListFragment(){
       return new NextShoppingListFragment();
   }
}
