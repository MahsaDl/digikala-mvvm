package com.example.digikalamvvm.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.ImageSliderLayoutItemBinding;
import com.example.digikalamvvm.databinding.RecyclerItemBinding;
import com.example.digikalamvvm.model.room.entity.SliderItem;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<SliderItem> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<SliderItem> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        ImageSliderLayoutItemBinding binding=ImageSliderLayoutItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SliderAdapterVH(binding);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderItem sliderItem = mSliderItems.get(position);

        viewHolder.binding.tvAutoImageSlider.setText(sliderItem.getDescription());
        viewHolder.binding.tvAutoImageSlider.setTextSize(16);
        viewHolder.binding.tvAutoImageSlider.setTextColor(Color.WHITE);
        Glide.with(viewHolder.binding.parentLayoutSlider)
                .load(sliderItem.getImageUrl())
                .fitCenter()
                .into(viewHolder.binding.ivAutoImageSlider);

        viewHolder.binding.parentLayoutSlider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        //View itemView;
        //ImageView imageViewBackground;
        //ImageView imageGifContainer;
        //TextView textViewDescription;

        ImageSliderLayoutItemBinding binding;

        public SliderAdapterVH(ImageSliderLayoutItemBinding itemView) {
            super(itemView.getRoot());
            //imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            //imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            //textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            //this.itemView = itemView;
            binding=itemView;
        }
    }

}