package com.example.leanbudget.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.CategoryList;

import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Category>> categoryList;

    public CategoryViewModel() {
        categoryList = new MutableLiveData<>();
        categoryList.setValue(CategoryList.getInstance());
    }

    public LiveData<ArrayList<Category>> getCategoryList() {
        return categoryList;
    }
}