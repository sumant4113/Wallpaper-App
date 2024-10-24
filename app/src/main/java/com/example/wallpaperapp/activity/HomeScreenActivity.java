package com.example.wallpaperapp.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wallpaperapp.R;
import com.example.wallpaperapp.adapter.CategoryRVAdapter;
import com.example.wallpaperapp.adapter.WallpaperRVAdapter;
import com.example.wallpaperapp.model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HomeScreenActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface {

    private RecyclerView categoryRV, wallpaperRV;
    private ProgressBar loadingPB;
    private ArrayList<CategoryModel> categoryModels = new ArrayList<>();
    private ArrayList<String> wallpaperArrayList = new ArrayList<>();
    private CategoryRVAdapter categoryRVAdapter;
    private WallpaperRVAdapter wallpaperRVAdapter;
    private EditText searchEdt;
    private ImageView searchIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        initView();
    }

    private void initView() {
        categoryRV = findViewById(R.id.idRVCategories);
        wallpaperRV = findViewById(R.id.idRVWallpapers);
        searchEdt = findViewById(R.id.idEdtSearch);
        searchIV = findViewById(R.id.idIVSearch);
        loadingPB = findViewById(R.id.idPBLoading);

        categoryModels.add(new CategoryModel("Technology", "https://images.unsplash.com/photo-1526374965328-7f61d4dc18c5?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTJ8fHRlY2hub2xvZ3l8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryModels.add(new CategoryModel("Programming", "https://images.unsplash.com/photo-1542831371-29b0f74f9713?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MXx8cHJvZ3JhbW1pbmd8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryModels.add(new CategoryModel("Nature", "https://images.pexels.com/photos/2387873/pexels-photo-2387873.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Travel", "https://images.pexels.com/photos/672358/pexels-photo-672358.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Architecture", "https://images.pexels.com/photos/256150/pexels-photo-256150.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Arts", "https://images.pexels.com/photos/1194420/pexels-photo-1194420.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Music", "https://images.pexels.com/photos/4348093/pexels-photo-4348093.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Abstract", "https://images.pexels.com/photos/2110951/pexels-photo-2110951.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Cars", "https://images.pexels.com/photos/3802510/pexels-photo-3802510.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Flowers", "https://images.pexels.com/photos/1086178/pexels-photo-1086178.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Quotes", "https://images.pexels.com/photos/5993292/pexels-photo-5993292.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Rain", "https://images.pexels.com/photos/459451/pexels-photo-459451.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Cute", "https://images.pexels.com/photos/1767434/pexels-photo-1767434.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Romance", "https://images.pexels.com/photos/984944/pexels-photo-984944.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Cat", "https://images.pexels.com/photos/416160/pexels-photo-416160.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Moon", "https://images.pexels.com/photos/821718/pexels-photo-821718.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("4k Wallpaper", "https://images.pexels.com/photos/807598/pexels-photo-807598.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Pretty Background", "https://images.pexels.com/photos/4906295/pexels-photo-4906295.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Feeling Happy", "https://images.pexels.com/photos/6231809/pexels-photo-6231809.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Pursuit of Portraits", "https://images.pexels.com/photos/13446698/pexels-photo-13446698.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Phone Wallpapers", "https://images.pexels.com/photos/3560024/pexels-photo-3560024.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("The Sun", "https://images.pexels.com/photos/671549/pexels-photo-671549.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Fashion", "https://images.pexels.com/photos/2907034/pexels-photo-2907034.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Relax, Take It Easy", "https://images.pexels.com/photos/4926950/pexels-photo-4926950.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Into The Woods", "https://images.pexels.com/photos/19885719/pexels-photo-19885719.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Beautiful Landscapes", "https://images.pexels.com/photos/3934023/pexels-photo-3934023.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Vertical Landscapes", "https://images.pexels.com/photos/9407837/pexels-photo-9407837.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Cities", "https://images.pexels.com/photos/16154734/pexels-photo-16154734.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));
        categoryModels.add(new CategoryModel("Books", "https://images.pexels.com/photos/1907785/pexels-photo-1907785.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"));


        LinearLayoutManager manager1 = new LinearLayoutManager(HomeScreenActivity.this, RecyclerView.HORIZONTAL, false);
        categoryRVAdapter = new CategoryRVAdapter(categoryModels, this, this);

        categoryRV.setLayoutManager(manager1);
        categoryRV.setAdapter(categoryRVAdapter);

        wallpaperRVAdapter = new WallpaperRVAdapter(wallpaperArrayList, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);

        wallpaperRV.setLayoutManager(layoutManager);
        wallpaperRV.setAdapter(wallpaperRVAdapter);

        getCategories();
        getWallpapers();

        searchIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchStr = searchEdt.getText().toString();
                if (searchStr.isEmpty()) {
                    Toast.makeText(HomeScreenActivity.this, "Please enter something to search", Toast.LENGTH_SHORT).show();
                } else {
                    getWallpapersByCategory(searchStr);
                }
            }
        });
    }

    private void getWallpapersByCategory(String category) {
        loadingPB.setVisibility(View.VISIBLE);
        String url = "https://api.pexels.com/v1/search?query=" + category + "&per_page=300&page=1";
        // Clear the list and notify the adapter
        wallpaperArrayList.clear();
        wallpaperRVAdapter.notifyDataSetChanged(); // Notify adapter before making the request

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingPB.setVisibility(View.INVISIBLE);
                try {
                    JSONArray photos = response.getJSONArray("photos");
                    for (int i = 0; i < photos.length(); i++) {
                        JSONObject photoObj = photos.getJSONObject(i);
                        String imgObj = photoObj.getJSONObject("src").getString("portrait");
                        wallpaperArrayList.add(imgObj);
                    }
                    wallpaperRVAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HomeScreenActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "cAuvy4zm5BwZVPURc4xoNgZdIoRlqczoWcFKqtAcgskw51tcxhnvBtzw");
                return headers;
            }

        };
        RequestQueue queue = Volley.newRequestQueue(HomeScreenActivity.this);
        queue.add(jsonObjectRequest);
    }


    private void getWallpapers() {
        loadingPB.setVisibility(View.VISIBLE);
        wallpaperArrayList.clear();
        wallpaperRVAdapter.notifyDataSetChanged();

        Collections.shuffle(categoryModels);
        String firstCategory = categoryModels.get(0).getCategory();
        getWallpapersByCategory(firstCategory);
    }

    private void getCategories() {
        SharedPreferences sharedPreferences = getSharedPreferences("WallpaperPrefs", MODE_PRIVATE);
        String lastCategoryName = sharedPreferences.getString("lastCategory", "");

        Collections.shuffle(categoryModels);
        CategoryModel newCategory = categoryModels.stream()
                .filter(category -> category.getCategory().equals(lastCategoryName))
                .findFirst()
                .orElse(categoryModels.get(0));

/*            CategoryModel newCategory = categoryModels.get(0);
            for (CategoryModel category : categoryModels) {
                if (!category.getCategory().equals(lastCategoryName)) {
                    newCategory = category;
                    break;
                }
            }*/
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastCategory", lastCategoryName);
        editor.apply();

    }


    @Override
    public void onCategoryClick(int position) {
        wallpaperArrayList.clear();
        loadingPB.setVisibility(View.VISIBLE);
        String category = categoryModels.get(position).getCategory();
        getWallpapersByCategory(category);
    }
}