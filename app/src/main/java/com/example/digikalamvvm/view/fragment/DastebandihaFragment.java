package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digikalamvvm.databinding.FragmentDastebandihaBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class DastebandihaFragment extends Fragment {

    FragmentDastebandihaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=FragmentDastebandihaBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.rlDigital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlKetab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlKhane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();

            }
        });
        binding.rlKhodro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlKodak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlSalamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlVarzesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlPoshak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
