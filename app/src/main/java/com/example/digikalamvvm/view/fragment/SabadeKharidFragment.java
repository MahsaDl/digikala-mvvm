package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digikalamvvm.databinding.FragmentSabadekharidBinding;
import com.example.digikalamvvm.di.component.DaggerMyViewPagerAdapterComponent;
import com.example.digikalamvvm.di.component.DaggerNextShoppingListFragmentComponent;
import com.example.digikalamvvm.di.component.DaggerNotificationFragmentComponent;
import com.example.digikalamvvm.di.component.DaggerShoppingListFragmentComponent;
import com.example.digikalamvvm.di.module.MyViewPagerAdapterModule;
import com.example.digikalamvvm.di.module.NextShoppingListFragmentModule;
import com.example.digikalamvvm.di.module.NotificationFragmentModule;
import com.example.digikalamvvm.di.module.ShoppingListFragmentModule;
import com.example.digikalamvvm.view.MyViewPagerAdapter;

import java.util.HashMap;
import java.util.Map;


public class SabadeKharidFragment extends Fragment {

    FragmentSabadekharidBinding binding;
    MyViewPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,  Bundle savedInstanceState) {
        binding=FragmentSabadekharidBinding.inflate(inflater);

        Map<String,Fragment> map= new HashMap<>();
        binding.viewPager.setOffscreenPageLimit(map.size());

        map.put("لیست خرید بعدی", DaggerNextShoppingListFragmentComponent.builder().nextShoppingListFragmentModule(new NextShoppingListFragmentModule()).build().getNextShoppingListFragment());
        map.put("سبد خرید", DaggerShoppingListFragmentComponent.builder().shoppingListFragmentModule(new ShoppingListFragmentModule()).build().getShoppingListFragment());

        //adapter=new MyViewPagerAdapter(getChildFragmentManager(),map);
        adapter= DaggerMyViewPagerAdapterComponent.builder().myViewPagerAdapterModule(new MyViewPagerAdapterModule(getChildFragmentManager(),map)).build().getMyViewPagerAdapter();
        binding.viewPager.setAdapter(adapter);
        binding.tab.setupWithViewPager(binding.viewPager);
        binding.tab.getTabAt(1).select();

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
