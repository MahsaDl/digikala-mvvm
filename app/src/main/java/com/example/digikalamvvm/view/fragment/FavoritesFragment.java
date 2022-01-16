package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentFavoritesBinding;
import com.example.digikalamvvm.di.component.DaggerFavoritesImageFragmentComponent;
import com.example.digikalamvvm.di.component.DaggerMyViewPagerAdapterFavoritesComponent;
import com.example.digikalamvvm.di.component.DaggerNotificationFragmentComponent;
import com.example.digikalamvvm.di.component.DaggerProductRepositoryComponent;
import com.example.digikalamvvm.di.module.FavoritesImageFragmentModule;
import com.example.digikalamvvm.di.module.MyViewPagerAdapterFavoritesModule;
import com.example.digikalamvvm.di.module.NotificationFragmentModule;
import com.example.digikalamvvm.di.module.ProductRepositoryModule;
import com.example.digikalamvvm.view.adapter.MyViewPagerAdapterFavorites;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {

    FragmentFavoritesBinding binding;
    MyViewPagerAdapterFavorites adapterFavorites;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,  Bundle savedInstanceState) {
        binding=FragmentFavoritesBinding.inflate(inflater);

        List<Fragment> list= new ArrayList<>();
        list.add(DaggerNotificationFragmentComponent.builder().notificationFragmentModule(new NotificationFragmentModule()).build().getNotificationFragment());
        list.add(DaggerFavoritesImageFragmentComponent.builder().favoritesImageFragmentModule(new FavoritesImageFragmentModule()).build().getFavoritesImageFragment());

        //adapterFavorites=new MyViewPagerAdapterFavorites(getChildFragmentManager(),list);
        adapterFavorites= DaggerMyViewPagerAdapterFavoritesComponent.builder().myViewPagerAdapterFavoritesModule(new MyViewPagerAdapterFavoritesModule(getChildFragmentManager(),list)).build().getMyViewPagerAdapterFavorites();
        binding.viewPagerFavorites.setAdapter(adapterFavorites);
        binding.tabFavorites.setupWithViewPager(binding.viewPagerFavorites);
        binding.tabFavorites.getTabAt(1).select();
        binding.tabFavorites.getTabAt(1).setIcon(R.drawable.ic_baseline_favorite_24);
        binding.tabFavorites.getTabAt(0).setIcon(R.drawable.ic_baseline_notifications_none_24);



        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
