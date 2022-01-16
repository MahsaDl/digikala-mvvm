package com.example.digikalamvvm.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapterFavorites extends FragmentPagerAdapter {
    List<Fragment> list;
    public MyViewPagerAdapterFavorites(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list =list;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }
}
