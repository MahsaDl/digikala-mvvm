package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.digikalamvvm.databinding.FragmentMydigikalaBinding;
import com.example.digikalamvvm.databinding.FragmentNextShoppingListBinding;


public class NextShoppingListFragment extends Fragment {
    FragmentNextShoppingListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentNextShoppingListBinding.inflate(inflater);
        return binding.getRoot();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
