package com.example.digikalamvvm.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentSettingBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class SettingFragment extends Fragment {
    FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentSettingBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.rlAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_settingFragment_to_aboutUsFragment);

            }
        });

        binding.rlGozaresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlPorsesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlSharayeteEstefsde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();

            }
        });
        binding.rlTamas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlEmtiaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"در حال ساخت", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        binding.rlTanzimat.setOnClickListener(new View.OnClickListener() {
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
