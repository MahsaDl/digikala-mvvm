package com.example.digikalamvvm.view.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.digikalamvvm.Const;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentMydigikalaBinding;
import com.example.digikalamvvm.view.MyBottomSheetDialog.MyBottomSheetDialogFragment;


public class MyDigikalaFragment extends Fragment {
    FragmentMydigikalaBinding binding;
    String userName;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentMydigikalaBinding.inflate(inflater);

        setHasOptionsMenu(true);

        binding.scroll.post(new Runnable() {
            @Override
            public void run() {
                binding.scroll.fullScroll(View.FOCUS_RIGHT);
            }
        });

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(Const.SHARED_PREF_NAME_LOGIN,MODE_PRIVATE);
        if (sharedPreferences.contains(Const.PREF_KEY_LOGIN_FLAG)){
            userName = sharedPreferences.getString(Const.PREF_KEY_USERNAME, "ali");


        }

        binding.tvUserName.setText(userName);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rlFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_nav_myDigikala_to_favoritesFragment);

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@androidx.annotation.NonNull Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_my_digikala,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@androidx.annotation.NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_setting:
                Navigation.findNavController(getView()).navigate(R.id.action_nav_myDigikala_to_settingFragment);
                break;
        }


        return super.onOptionsItemSelected(item);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
