package com.example.wallpaperapp.activity;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.wallpaperapp.R;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.IOException;

public class WallpaperActivity extends AppCompatActivity {

    private WallpaperManager wallpaperManager;
    private ImageView image;
    private String url;
    private ProgressBar loadingPB;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);

        url = getIntent().getStringExtra("imgUrl");
        image = findViewById(R.id.image);
        loadingPB = findViewById(R.id.idPBLoading);

        Glide.with(this).load(url).override(Target.SIZE_ORIGINAL).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                loadingPB.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                loadingPB.setVisibility(View.GONE);
                return false;
            }
        }).into(image);

        wallpaperManager = WallpaperManager.getInstance(WallpaperActivity.this);
        Button setWallpaper = findViewById(R.id.idBtnSetWallpaper);

        setWallpaper.setOnClickListener(v -> {
            Glide.with(WallpaperActivity.this)
                    .asBitmap().load(url)
                    .override(Target.SIZE_ORIGINAL)
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Bitmap> target, boolean isFirstResource) {
                            Toast.makeText(WallpaperActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(@NonNull Bitmap resource, @NonNull Object model, Target<Bitmap> target, @NonNull DataSource dataSource, boolean isFirstResource) {

                            try {
                                wallpaperManager.setBitmap(resource);
                                runOnUiThread(() -> FancyToast.makeText(WallpaperActivity.this, "Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show());

                                returnToHomeScreen();
                            } catch (IOException e) {
                                runOnUiThread(() -> Toast.makeText(WallpaperActivity.this, "Fail to Set Wallpaper", Toast.LENGTH_SHORT).show());
                                e.printStackTrace();
                            }
                            return false;
                        }
                    }).submit();

            runOnUiThread(() -> FancyToast.makeText(WallpaperActivity.this, "Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show());
//            runOnUiThread(() -> FancyToast.makeText(WallpaperActivity.this, "Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show());
//            runOnUiThread(() -> FancyToast.makeText(WallpaperActivity.this, "Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG, FancyToast.DEFAULT, false).show());
//            runOnUiThread(() -> FancyToast.makeText(WallpaperActivity.this, "Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG, FancyToast.CONFUSING, false).show());
//            runOnUiThread(() -> FancyToast.makeText(WallpaperActivity.this, "Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG, FancyToast.WARNING, false).show());
//            runOnUiThread(() -> FancyToast.makeText(WallpaperActivity.this, "Wallpaper Set To Home Screen", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show());

        });

    }

    private void returnToHomeScreen() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        finishAffinity();
    }
}