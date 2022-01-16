package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.digikalamvvm.R;
import com.example.digikalamvvm.model.room.entity.Resources;
import com.example.digikalamvvm.view.VerticalSpacingItemDecoration;
import com.example.digikalamvvm.databinding.FragmentMediaBinding;
import com.example.digikalamvvm.di.component.DaggerVideoPlayerRecyclerAdapterComponent;
import com.example.digikalamvvm.di.module.VideoPlayerRecyclerAdapterModule;
import com.example.digikalamvvm.model.room.entity.MediaObject;
import com.example.digikalamvvm.view.adapter.VideoPlayerRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class MediaFragment extends Fragment {

    FragmentMediaBinding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable  ViewGroup container,  Bundle savedInstanceState) {
        binding=FragmentMediaBinding.inflate(inflater);
        initRecyclerView();

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void initRecyclerView(){
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        VerticalSpacingItemDecoration itemDecorator = new VerticalSpacingItemDecoration(15);
        binding.recyclerView.addItemDecoration(itemDecorator);

        ArrayList<MediaObject> mediaObjects = new ArrayList<MediaObject>(Arrays.asList(Resources.MEDIA_OBJECTS));
        binding.recyclerView.setMediaObjects(mediaObjects);
        //VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(mediaObjects, initGlide());
        VideoPlayerRecyclerAdapter adapter = DaggerVideoPlayerRecyclerAdapterComponent.builder().videoPlayerRecyclerAdapterModule(new VideoPlayerRecyclerAdapterModule(mediaObjects, initGlide())).build().getVideoPlayerRecyclerAdapter();
        binding.recyclerView.setAdapter(adapter);
    }

    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    @Override
    public void onStop() {
        if(binding.recyclerView!=null)
            binding.recyclerView.releasePlayer();
        super.onStop();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        binding=null;

    }


}
