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
            mockData();
            addGeneralCategory();
        }
        return instance;
    }

    private static void addGeneralCategory() {
        Category general = new Category("General", R.drawable.ic_cart);
        instance.add(general);
    }

    public static Category getCategory(String name) {
        for (int i = 0; i < instance.size(); i++) {
            Category category = instance.get(i);
            if (category.getName().equals(name)) {
                return category;
            }
        }
        return null;
    }

    private static void mockData() {
        Category sportsCategory = new Category("Sports", R.drawable.ic_sport);
        Category electronicsCategory = new Category("Electronics", R.drawable.ic_electronics);
        Category foodCategory = new Category("Food", R.drawable.ic_cart);
        instance.add(sportsCategory);
        instance.add(electronicsCategory);
        instance.add(foodCategory);
    }
}
