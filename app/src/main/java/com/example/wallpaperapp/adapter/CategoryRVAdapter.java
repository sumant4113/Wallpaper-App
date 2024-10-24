package com.example.wallpaperapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wallpaperapp.model.CategoryModel;
import com.example.wallpaperapp.R;

import java.util.ArrayList;

public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.ViewHolder> {

    private ArrayList<CategoryModel> categoryModelList;
    private Context context;
    private CategoryClickInterface categoryClickInterFace;

    public CategoryRVAdapter(ArrayList<CategoryModel> categoryModelList, Context context, CategoryClickInterface categoryClickInterFace) {
        this.categoryModelList = categoryModelList;
        this.context = context;
        this.categoryClickInterFace = categoryClickInterFace;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel model = categoryModelList.get(position);

        holder.CategoryTV.setText(model.getCategory());
        if (!model.getImhUrl().isEmpty()) {
            Glide.with(context).load(model.getImhUrl()).into(holder.CategoryIV);
        } else {
            holder.CategoryIV.setImageResource(R.drawable.ic_launcher_background);
        }
        holder.itemView.setOnClickListener(v -> {
            categoryClickInterFace.onCategoryClick(position);

            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView CategoryIV;
        private TextView CategoryTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            CategoryIV = itemView.findViewById(R.id.idIVCategory);
            CategoryTV = itemView.findViewById(R.id.idTVCategory);
        }
    }

    public interface CategoryClickInterface {
        void onCategoryClick(int position);
    }
}
