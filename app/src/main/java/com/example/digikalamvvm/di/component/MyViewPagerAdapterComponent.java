package com.example.digikalamvvm.di.component;

import com.example.digikalamvvm.di.module.MyViewPagerAdapterModule;
import com.example.digikalamvvm.di.module.ProductAdapterModule;
import com.example.digikalamvvm.view.MyViewPagerAdapter;
import com.example.digikalamvvm.view.adapter.ProductAdapter;

import dagger.Component;

@Component(modules = MyViewPagerAdapterModule.class)
public interface MyViewPagerAdapterComponent {
    MyViewPagerAdapter getMyViewPagerAdapter();

}
