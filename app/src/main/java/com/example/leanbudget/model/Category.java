package com.example.leanbudget.model;

import androidx.annotation.NonNull;

public class Category {

    private String name;
    private int iconId;

    public Category(String name, int imageId) {
        this.name = name;
        this.iconId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
