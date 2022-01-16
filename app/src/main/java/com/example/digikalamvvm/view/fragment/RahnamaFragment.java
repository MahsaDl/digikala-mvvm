package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.example.digikalamvvm.databinding.FragmentRahnamaBinding;


public class RahnamaFragment extends Fragment {
    FragmentRahnamaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentRahnamaBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
