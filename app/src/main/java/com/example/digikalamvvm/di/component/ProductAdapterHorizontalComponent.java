package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.ProductAdapterHorizontalModule;
import com.example.digikalamvvm.di.module.ProductAdapterModule;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.ProductAdapterHorizontal;

import dagger.Component;

@Component(modules = ProductAdapterHorizontalModule.class)
public interface ProductAdapterHorizontalComponent {
    ProductAdapterHorizontal getProductAdapterHorizontal();
    //void inject(HomeFragment homeFragment);
}
