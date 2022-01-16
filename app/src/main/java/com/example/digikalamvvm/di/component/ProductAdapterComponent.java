package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.ProductAdapterModule;
import com.example.digikalamvvm.view.adapter.ProductAdapter;

import dagger.Component;

@Component(modules = ProductAdapterModule.class)
public interface ProductAdapterComponent {
    ProductAdapter getProductAdapter();
    //void inject(HomeFragment homeFragment);
}
