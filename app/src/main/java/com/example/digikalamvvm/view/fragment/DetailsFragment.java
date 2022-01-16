package com.example.digikalamvvm.view.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.fonts.FontFamily;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentDetailsBinding;

import com.example.digikalamvvm.model.room.entity.Product;
import com.example.digikalamvvm.view.MyBottomSheetDialog.MyBottomSheetDialogFragment;
import com.example.digikalamvvm.view.activity.BaseActivity;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import pl.droidsonroids.gif.GifImageView;


public class DetailsFragment extends Fragment {
    FragmentDetailsBinding binding;
    ProductViewmodel viewmodel;
    Product productDelete;
    int id;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentDetailsBinding.inflate(inflater);
        viewmodel= ViewModelProviders.of(this).get(ProductViewmodel.class);
        id=getArguments().getInt("id");


        viewmodel.selectById(id).subscribe(new SingleObserver<Product>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull Product product) {
                productDelete=product;
                binding.tvNameDetail.setText(product.getName());
                binding.tvAnbarDetails.setText(product.getAnbar());
                binding.tvModelDetail.setText(product.getModel());
                binding.tvPriceDetails.setText(product.getPrice());
                binding.ratingBar.setRating(load());
                //Picasso.get()
                        //.load(product.getImageUrl() )
                        //.placeholder(R.drawable.ic_launcher_background)
                        //.into(binding.imgDetail);

                if(product.getImageUrl().contains("http")){
                    Picasso.get()
                            .load(product.getImageUrl())
                            .into(binding.imgDetail);
                }else {
                    Glide.with(getActivity())
                            .load(Uri.parse(product.getImageUrl())) // Uri of the picture
                            .into(binding.imgDetail);

                }



            }

            @Override
            public void onError(@NonNull Throwable e) {
                Snackbar.make(getView(),"خطا در برقراری ارتباط با دیتابیس", BaseTransientBottomBar.LENGTH_LONG).show();

            }
        });


        //((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        setHasOptionsMenu(true);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*binding.imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_detailsFragment_to_mainActivity);
            }
        });

        binding.imgBtnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });*/


        binding.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                save(rating);


            }
        });




        binding.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {

                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},0);
                }else
                {
                    call();
                }
            }
        });

        binding.btnPayamak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str="sms:+98912111";
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(str));

                if (intent.resolveActivity(getActivity().getPackageManager())!=null)
                {
                    getActivity().startActivity(intent);
                }else
                {
                    Toast.makeText(getContext(),"Not found",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void save(float f){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("folder",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putFloat("rating"+id,f);
        editor.apply();
    }
    public float load(){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("folder",MODE_PRIVATE);
        float f=sharedPreferences.getFloat("rating"+id,0f);
        return f;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==0 && grantResults.length>0)
        {
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                call();

            }else
            {
                Snackbar.make(getView(),"شما مجوز برقراری تماس را به دیجی کالا نداده اید           ", BaseTransientBottomBar.LENGTH_LONG).show();

            }
        }
    }




    @Override
    public void onCreateOptionsMenu(@androidx.annotation.NonNull Menu menu,MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_details,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@androidx.annotation.NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_more:
                MyBottomSheetDialogFragment myBottomSheetDialogFragment=new MyBottomSheetDialogFragment();
                myBottomSheetDialogFragment.show(getActivity().getSupportFragmentManager(),myBottomSheetDialogFragment.getTag());
                break;
            case R.id.action_delete:
                //viewmodel.delete(productDelete);
                //Navigation.findNavController(getView()).navigate(R.id.action_detailsFragment_to_nav_home);
                deleteProductDialog();
                break;

            case R.id.action_update:
                Bundle bundle=new Bundle();
                bundle.putInt("id",productDelete.getId());
                bundle.putString("name",productDelete.getName());
                bundle.putString("price",productDelete.getPrice());
                bundle.putString("imageUrl",productDelete.getImageUrl());
                bundle.putString("model",productDelete.getModel());

                Navigation.findNavController(getView()).navigate(R.id.action_detailsFragment_to_nav_update,bundle);
                break;

        }


        return super.onOptionsItemSelected(item);

    }

    private void call()
    {
        String str="tel:+98912111";
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse(str));

        if (intent.resolveActivity(getActivity().getPackageManager())!=null)
        {
            getActivity().startActivity(intent);
        }else
        {
        }


    }


    public void deleteProductDialog(){
        AlertDialog alertDialog;
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        TextView textView = new TextView(getActivity());
        textView.setText("آیا از حذف آگهی مطمئن هستید؟");
        textView.setTextColor(Color.BLACK);
        textView.setPadding(20, 50, 80, 30);
        builder.setCustomTitle(textView);

        builder.setNegativeButton("خیر", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewmodel.delete(productDelete);
                Navigation.findNavController(getView()).navigate(R.id.action_detailsFragment_to_nav_home);
                Toast.makeText(getActivity(),"آگهی باموفقیت حذف شد.",Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog=builder.create();
        alertDialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }

}
