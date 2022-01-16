package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.digikalamvvm.databinding.FragmentFavoritesImageBinding;
import com.example.digikalamvvm.databinding.FragmentNextShoppingListBinding;


public class FavoritesImageFragment extends Fragment {
    FragmentFavoritesImageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentFavoritesImageBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
