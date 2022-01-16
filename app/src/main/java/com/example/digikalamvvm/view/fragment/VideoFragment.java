package com.example.digikalamvvm.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentShoppingListBinding;
import com.example.digikalamvvm.databinding.FragmentVideoBinding;
import com.example.digikalamvvm.di.component.DaggerProductAdapterHorizontalComponent;
import com.example.digikalamvvm.di.component.DaggerVideoAdapterComponent;
import com.example.digikalamvvm.di.module.ProductAdapterHorizontalModule;
import com.example.digikalamvvm.di.module.VideoAdapterModule;
import com.example.digikalamvvm.model.room.entity.Video;
import com.example.digikalamvvm.view.VideoSpacingItemDecorator;
import com.example.digikalamvvm.view.adapter.VideoAdapter;

import java.util.ArrayList;
import java.util.List;


public class VideoFragment extends Fragment {
    FragmentVideoBinding binding;
    VideoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        binding=FragmentVideoBinding.inflate(inflater);

        //adapter=new VideoAdapter(getvideo());
        adapter= DaggerVideoAdapterComponent.builder().videoAdapterModule(new VideoAdapterModule(getvideo())).build().getVideoAdapter();
        binding.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setAdapter(adapter);

        VideoSpacingItemDecorator itemDecorator=new VideoSpacingItemDecorator(15);
        binding.recycler.addItemDecoration(itemDecorator);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.recycler.setAdapter(adapter);

    }

    @Override
    public void onStop() {
        binding.recycler.setAdapter(null);

        super.onStop();
    }

    List<Video> getvideo(){
        List<Video> list=new ArrayList<>();
        list.add(new Video("https://dl.rozmusic.com/Music%20Video/1396/05/22/Sirvan%20Khosravi%20-%20Khoshhalam%20480p_RozMusic.mp4", getActivity(),R.drawable.sirvan,"Sirvan_khosravi","Khoshhalam :)",". 10K views",". 4 days ago"));

        list.add(new Video("https://dl.rozmusic.com/Music%20Video/1396/11/28/Ashvan%20-%20Mano%20Daryab%20720p_RozMusic.mp4", getActivity(),R.drawable.ashvan,"Ashvan_official","Mano Daryab",". 59K views",". 3 weeks ago"));
        list.add(new Video("https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Sending+Data+to+a+New+Activity+with+Intent+Extras.mp4", getActivity(),R.drawable.codingwithmitch,"CodingWithMitch","Sending Data to a New Activity with Intent Extras",". 665K views",". 8 months ago"));
        list.add(new Video("https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/REST+API+Retrofit+MVVM+Course+Summary.mp4", getActivity(),R.drawable.codingwithdev,"Coding With Dev","REST API, Retrofit2, MVVM Course SUMMARY",". 103 views",". 5 weeks ago"));
        list.add(new Video("http://dl2.sarimusic.net/1396/10/10/Ebi%20-%20Nafas%20Nafas%20(480p)%20[SariMusic.IR].mp4", getActivity(),R.drawable.ebi,"Ebi","Nafas Nafas Remix",". 1.9M views",". 21 hours ago"));
        list.add(new Video("https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/SwipingViewPager+Tutorial.mp4", getActivity(),R.drawable.codingwithmitch,"CodingWithMitch","Swiping Views with a ViewPager",". 2.7M views",". 1 year ago"));
        list.add(new Video("https://s3.ca-central-1.amazonaws.com/codingwithmitch/media/VideoPlayerRecyclerView/Rest+api+teaser+video.mp4", getActivity(),R.drawable.stevdzan,"Stevdza-San","Database Cache, MVVM, Retrofit, REST API",". 13K views",". 3 days ago"));

        return list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
}
