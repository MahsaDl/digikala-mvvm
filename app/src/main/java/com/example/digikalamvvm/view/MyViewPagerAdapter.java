package com.example.digikalamvvm.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.Map;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    Map<String,Fragment> map;
    public MyViewPagerAdapter(@NonNull FragmentManager fm,Map<String,Fragment> map) {
        super(fm);
        this.map=map;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) map.keySet().toArray()[position];
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return (Fragment) map.values().toArray()[position];
    }

    @Override
    public int getCount() {
        return map.size();
    }
}
