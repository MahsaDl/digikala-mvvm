package com.example.digikalamvvm.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.HorizontalRecyclerviewItemsBinding;
import com.example.digikalamvvm.databinding.RecyclerItemBinding;
import com.example.digikalamvvm.model.room.entity.Product;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapterHorizontal extends RecyclerView.Adapter<ProductAdapterHorizontal.MyViewHolder>{

    private ProductViewmodel viewmodel;
    private List<Product> list;
    Context context;
    public ProductAdapterHorizontal(ProductViewmodel productViewmodel) {
        this.list=new ArrayList<>();
        this.viewmodel=productViewmodel;
    }
    public void setList(List<Product> list){
        this.list =list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(  ViewGroup parent, int viewType) {
        HorizontalRecyclerviewItemsBinding binding=HorizontalRecyclerviewItemsBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context=parent.getContext();
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ProductAdapterHorizontal.MyViewHolder holder, int position) {
        Product product=this.list.get(position);

        holder.binding.tvName.setText(product.getName());
        holder.binding.tvAnbar.setText(product.getAnbar());
        holder.binding.tvModel.setText(product.getModel());
        holder.binding.tvPrice.setText(product.getPrice());



        if(product.getImageUrl().contains("http")){
            Picasso.get()
                    .load(product.getImageUrl())
                    .into(holder.binding.imgCardview);
        }else {
            //Picasso.get().load(Uri.parse(product.getImageUrl())).into(holder.binding.imgCardview);
            Glide.with(context)
                    .load(Uri.parse(product.getImageUrl())) // Uri of the picture
                    .into(holder.binding.imgCardview);

        }

        holder.binding.parentLayoutHorizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",product.getId());
                Navigation.findNavController(holder.itemView).navigate(R.id.action_nav_home_to_detailsFragment,bundle);


            }
        });




    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder{
        HorizontalRecyclerviewItemsBinding binding;

        public MyViewHolder( HorizontalRecyclerviewItemsBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;

        }
    }
}
