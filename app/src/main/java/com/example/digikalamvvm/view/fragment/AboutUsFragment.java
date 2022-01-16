package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digikalamvvm.databinding.FragmentAboutUsBinding;



public class AboutUsFragment extends Fragment {

    FragmentAboutUsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentAboutUsBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
