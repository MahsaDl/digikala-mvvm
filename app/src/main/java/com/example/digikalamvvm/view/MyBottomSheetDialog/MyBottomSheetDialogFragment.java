package com.example.digikalamvvm.view.MyBottomSheetDialog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.digikalamvvm.databinding.FragmentMyBottomsheetDialogBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    FragmentMyBottomsheetDialogBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        binding=FragmentMyBottomsheetDialogBinding.inflate(inflater);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tvPriceChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlChart = "https://www.digikala.com/";
                Intent intentChart = new Intent(Intent.ACTION_VIEW);
                intentChart.setData(Uri.parse(urlChart));
                startActivity(intentChart);
            }
        });

        binding.tvCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlCompare = "https://www.digikala.com/promotion-center/";
                Intent intentCompare = new Intent(Intent.ACTION_VIEW);
                intentCompare.setData(Uri.parse(urlCompare));
                startActivity(intentCompare);
            }
        });

        binding.tvShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Url = "";
                Intent intentShare = new Intent(Intent.ACTION_SEND);
                intentShare.setType("text/plain");
                intentShare.putExtra(Intent.EXTRA_TEXT, Url);
                startActivity(Intent.createChooser(intentShare, "Share : "));
            }
        });

        binding.tvAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "اضافه شد", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
