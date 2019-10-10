package com.example.leanbudget.model;

import com.example.leanbudget.R;

import java.util.ArrayList;

public class CategoryList {
    private static ArrayList<Category> instance;

    private CategoryList() {
        // Singleton
    }

    public static ArrayList<Category> getInstance() {
        if (instance == null) {
            instance = new ArrayList<>();
            addGeneralCategory();
        }
        return instance;
    }

    private static void addGeneralCategory() {
        Category general = new Category("General", R.drawable.ic_cart);
        instance.add(general);
    }
}
