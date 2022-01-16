package com.example.digikalamvvm.view.fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentInsertProductBinding;
import com.example.digikalamvvm.model.room.entity.Product;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;


public class InsertProductFragment extends Fragment {
    FragmentInsertProductBinding binding;
    public static final int PICK_IMAGE=1;
    Uri imageUri;
    ProductViewmodel viewModel;
    //Bitmap bitmap;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentInsertProductBinding.inflate(inflater);

        viewModel= ViewModelProviders.of(this).get(ProductViewmodel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bitmap bitmap = BitmapFactory.decodeFile(binding.img.toString());

                Product product=new Product (0,String.valueOf(imageUri) ,binding.etName.getText().toString(),binding.etPrice.getText().toString(),"موجود در انبار دیجی کالا",binding.etModel.getText().toString());

                if (checkInsert(product)){
                    viewModel.insert(product);
                    Toast.makeText(getActivity(),"آگهی شما باموفقیت منتشر شد.",Toast.LENGTH_LONG).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_nav_insert_product_to_nav_home);
                }

            }
        });
        binding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestForPermission();
            }
        });

        binding.rlRahnama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_nav_insert_product_to_rahnamaFragment);
            }
        });
    }

    public boolean checkInsert(Product product) {
        if (product.getName()==null || product.getName().equals("")) {
            binding.etName.setError("لطفا نام کالا را وارد کنید");
            binding.etName.requestFocus();
            return false;
        }
        if (product.getModel() == null || product.getModel().equals("")) {
            binding.etModel.setError("لطفا مدل کالا را وارد کنید");
            binding.etModel.requestFocus();
            return false;
        }
        if (product.getPrice() == null || product.getPrice().equals("")) {
            binding.etPrice.setError("لطفا قیمت کالا را وارد کنید");
            binding.etPrice.requestFocus();
            return false;
        }

        return true;
    }

    private void checkAndRequestForPermission(){
        if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);
        }else
        {
            openGallery();
        }
    }
    private void openGallery(){
        Intent gallery=new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(Intent.createChooser(gallery,"کلیک کنید"),PICK_IMAGE);


    }




    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==0 && grantResults.length>0)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                openGallery();

            }else
            {
                Snackbar.make(getView(),"شما مجوز دسترسی به حافظه ی گوشی را به دیجی کالا نداده اید", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PICK_IMAGE && resultCode==RESULT_OK )
        {
            imageUri=data.getData();
            //try {
                //bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
            //} catch (IOException e) {
                //e.printStackTrace();
            //}
            binding.img.setImageURI(imageUri);
            //binding.img.setImageBitmap(bitmap);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
