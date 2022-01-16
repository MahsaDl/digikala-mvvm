package com.example.digikalamvvm.di.module;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.digikalamvvm.view.adapter.MyViewPagerAdapterFavorites;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import java.util.List;
import java.util.Map;

import dagger.Module;
import dagger.Provides;

@Module
public class MyViewPagerAdapterFavoritesModule {
    private List<Fragment> list;
    private FragmentManager childFragmentManager;

    public MyViewPagerAdapterFavoritesModule( FragmentManager childFragmentManager,List<Fragment> list) {
        this.childFragmentManager = childFragmentManager;
        this.list = list;
    }

    @Provides
    public MyViewPagerAdapterFavorites myViewPagerAdapterFavorites(){
        return new MyViewPagerAdapterFavorites(childFragmentManager,list);
    }
}
