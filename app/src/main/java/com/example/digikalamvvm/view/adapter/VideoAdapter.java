package com.example.digikalamvvm.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digikalamvvm.databinding.FragmentVideoBinding;
import com.example.digikalamvvm.databinding.VideoListItemBinding;
import com.example.digikalamvvm.model.room.entity.Video;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    List<Video> mDataset;
    Context context;

    public VideoAdapter(List<Video> list) {
        this.mDataset = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();

        VideoListItemBinding binding= VideoListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new VideoAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        initPlayer((ViewHolder) holder,position);
        holder.binding.userName.setText(mDataset.get(position).getUserName());
        holder.binding.title.setText(mDataset.get(position).getTitle());
        holder.binding.views.setText(mDataset.get(position).getViews());
        holder.binding.time.setText(mDataset.get(position).getTime());
        holder.binding.imgProfile.setImageResource(mDataset.get(position).getImgProfile());

    }

    private void initPlayer(ViewHolder holder,int position) {
        mDataset.get(position).setPlayer(ExoPlayerFactory.newSimpleInstance(
                context,new DefaultRenderersFactory(mDataset.get(position).getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl()));


        holder.binding.videoPlayerView.setPlayer(mDataset.get(position).getPlayer());

        mDataset.get(position).getPlayer().setPlayWhenReady(mDataset.get(position).getPlayWhenReady());
        mDataset.get(position).getPlayer().seekTo(mDataset.get(position).getCurrentWindow(), mDataset.get(position).getPlaybackPosition());

        Uri uri = Uri.parse(mDataset.get(position).getUrl());
        MediaSource mediaSource = buildMediaSource(uri);
        mDataset.get(position).getPlayer().prepare(mediaSource, false, false);
    }

    private MediaSource buildMediaSource(Uri uri) {

        return new ExtractorMediaSource.Factory(
                new DefaultHttpDataSourceFactory("exoplayer-codelab")).
                createMediaSource(uri);
    }



    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    @Override
    public void onViewRecycled(@NonNull ViewHolder holder) {
        int position = holder.getAdapterPosition();
        SimpleExoPlayer player = mDataset.get(position).getPlayer();
        if (player != null) {

            mDataset.get(position).setCurrentWindow(player.getCurrentWindowIndex());
            mDataset.get(position).setPlaybackPosition(player.getCurrentPosition());
            mDataset.get(position).setPlayWhenReady(player.getPlayWhenReady());
            player.release();
            player=null;
        }
        super.onViewRecycled(holder);
    }










    class ViewHolder extends RecyclerView.ViewHolder{
        //PlayerView playerView;
        //TextView userName,title,views,time;
        VideoListItemBinding binding;

        public ViewHolder(@NonNull VideoListItemBinding itemView) {
            super(itemView.getRoot());
            binding=itemView;
        }
    }
}
