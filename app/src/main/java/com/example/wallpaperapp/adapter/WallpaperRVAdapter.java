package com.example.wallpaperapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.activity.WallpaperActivity;

import java.util.ArrayList;

public class WallpaperRVAdapter extends RecyclerView.Adapter<WallpaperRVAdapter.MyViewholder> {

    private ArrayList<String> wallpaperList;
    private Context context;

    public WallpaperRVAdapter(ArrayList<String> wallpaperList, Context context) {
        this.wallpaperList = wallpaperList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallpepar_rv_item, parent, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        String wallpaperPath = wallpaperList.get(position);

        Glide.with(context).load(wallpaperPath).override(Target.SIZE_ORIGINAL).into(holder.idIVWallpaper);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, WallpaperActivity.class);
            intent.putExtra("imgUrl", wallpaperPath);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return wallpaperList.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder {
        private ImageView idIVWallpaper;
        private CardView idCVWallpaper;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            idIVWallpaper = itemView.findViewById(R.id.idIVWallpaper);
            idCVWallpaper = itemView.findViewById(R.id.idCVWallpaper);
        }
    }

}
