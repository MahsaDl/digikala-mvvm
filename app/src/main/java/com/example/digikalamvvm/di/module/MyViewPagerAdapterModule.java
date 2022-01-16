package com.example.digikalamvvm.di.module;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.digikalamvvm.view.MyViewPagerAdapter;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import java.util.Map;

import dagger.Module;
import dagger.Provides;

@Module
public class MyViewPagerAdapterModule {
    private Map<String, Fragment> map;
    private FragmentManager childFragmentManager;

    public MyViewPagerAdapterModule( FragmentManager childFragmentManager,Map<String, Fragment> map) {
        this.childFragmentManager = childFragmentManager;
        this.map = map;
    }

    @Provides
    public MyViewPagerAdapter myViewPagerAdapter(){
        return new MyViewPagerAdapter(childFragmentManager,map);
    }
}
