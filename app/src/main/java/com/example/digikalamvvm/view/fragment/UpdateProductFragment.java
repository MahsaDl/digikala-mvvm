package com.example.digikalamvvm.view.fragment;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentInsertProductBinding;
import com.example.digikalamvvm.databinding.FragmentUpdateProductBinding;
import com.example.digikalamvvm.model.room.entity.Product;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;


public class UpdateProductFragment extends Fragment {
    FragmentUpdateProductBinding binding;
    public static final int PICK_IMAGE=1;
    Uri imageUri=null;
    ProductViewmodel viewModel;
    Product product;
    int id;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentUpdateProductBinding.inflate(inflater);

        viewModel= ViewModelProviders.of(this).get(ProductViewmodel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments()!=null){
            id = getArguments().getInt("id");

            binding.etNameUpdate.setText(getArguments().getString("name"));
            binding.etPriceUpdate.setText(getArguments().getString("price"));
            binding.etModelUpdate.setText(getArguments().getString("model"));
            //binding.img.setImageURI(Uri.parse(getArguments().getString("imageUrl")));

            //Picasso.get().load(Uri.parse(getArguments().getString("imageUrl"))).into(binding.imgUpdate);
            if((getArguments().getString("imageUrl")).contains("http")){
                Picasso.get()
                        .load(Uri.parse(getArguments().getString("imageUrl")))
                        .into(binding.imgUpdate);
            }else {
                Glide.with(getContext())
                        .load(Uri.parse(getArguments().getString("imageUrl"))) // Uri of the picture
                        .into(binding.imgUpdate);

            }

        }


        binding.reletiveUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_updateFragment_to_rahnamaVirayeshFragment);

            }
        });


        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bitmap bitmap = BitmapFactory.decodeFile(binding.img.toString());
                if (imageUri==null)
                {
                    //product=new Product(id,getArguments().getString("imageUrl") ,binding.etNameUpdate.getText().toString(),binding.etPriceUpdate.getText().toString(),"موجود در انبار دیجی کالا",binding.etModelUpdate.getText().toString());
                    product=new Product();
                    product.setId(id);
                    product.setName(binding.etNameUpdate.getText().toString());
                    product.setAnbar("موجود در انبار دیجی کالا");
                    product.setPrice(binding.etPriceUpdate.getText().toString());
                    product.setModel(binding.etModelUpdate.getText().toString());
                    product.setImageUrl(getArguments().getString("imageUrl"));

                }else {
                    //product=new Product(id,String.valueOf(imageUri) ,binding.etNameUpdate.getText().toString(),binding.etPriceUpdate.getText().toString(),"موجود در انبار دیجی کالا",binding.etModelUpdate.getText().toString());
                    product=new Product();

                    product.setId(id);
                    product.setName(binding.etNameUpdate.getText().toString());
                    product.setAnbar("موجود در انبار دیجی کالا");
                    product.setPrice(binding.etPriceUpdate.getText().toString());
                    product.setModel(binding.etModelUpdate.getText().toString());
                    product.setImageUrl(String.valueOf(imageUri));

                }
                if (checkUpdate(product)){
                    viewModel.update(product);
                    Toast.makeText(getActivity(),"آگهی شما باموفقیت ویرایش شد.",Toast.LENGTH_LONG).show();
                    //Navigation.findNavController(getView()).navigate(R.id.action_updateFragment_to_detailsFragment);
                }



            }
        });
        binding.imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestForPermission();
            }
        });
    }

    public boolean checkUpdate(Product product) {
        if (product.getName()==null || product.getName().equals("")) {
            binding.etNameUpdate.setError("لطفا نام کالا را وارد کنید");
            binding.etNameUpdate.requestFocus();
            return false;
        }
        if (product.getModel() == null || product.getModel().equals("")) {
            binding.etModelUpdate.setError("لطفا مدل کالا را وارد کنید");
            binding.etModelUpdate.requestFocus();
            return false;
        }
        if (product.getPrice() == null || product.getPrice().equals("")) {
            binding.etPriceUpdate.setError("لطفا قیمت کالا را وارد کنید");
            binding.etPriceUpdate.requestFocus();
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
            //Bitmap bitmap= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
            binding.imgUpdate.setImageURI(imageUri);


            }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
