package com.example.digikalamvvm.di.module;


import com.example.digikalamvvm.view.fragment.NextShoppingListFragment;
import com.example.digikalamvvm.view.fragment.ShoppingListFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class ShoppingListFragmentModule {

  @Provides
   public ShoppingListFragment provideShoppingListFragment(){
       return new ShoppingListFragment();
   }
}
