package com.example.wallpaperapp.model;

public class CategoryModel {

    String category;
    String imhUrl;

    public CategoryModel(String category, String imhUrl) {
        this.category = category;
        this.imhUrl = imhUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImhUrl() {
        return imhUrl;
    }

    public void setImhUrl(String imhUrl) {
        this.imhUrl = imhUrl;
    }
}
