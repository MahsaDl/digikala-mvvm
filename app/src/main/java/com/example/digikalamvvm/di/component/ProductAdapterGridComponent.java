package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.ProductAdapterGridModule;
import com.example.digikalamvvm.di.module.ProductAdapterModule;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.ProductAdapterGrid;

import dagger.Component;

@Component(modules = ProductAdapterGridModule.class)
public interface ProductAdapterGridComponent {
    ProductAdapterGrid getProductAdapterGrid();
    //void inject(HomeFragment homeFragment);
}
