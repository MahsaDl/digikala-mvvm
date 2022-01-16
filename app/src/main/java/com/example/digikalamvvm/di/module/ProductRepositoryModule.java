package com.example.digikalamvvm.di.module;


import android.app.Application;

import com.example.digikalamvvm.model.repository.ProductRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductRepositoryModule {
    private Application application;

   public ProductRepositoryModule(Application application) {
        this.application = application;
   }

  @Provides
   public ProductRepository provideRepository(){
       return new ProductRepository(application);
   }
}
