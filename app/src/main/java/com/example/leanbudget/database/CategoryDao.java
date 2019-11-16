package com.example.leanbudget.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.Expense;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    void insert(Expense expense);

    @Update
    void update(Expense expense);

    @Delete
    void delete(Expense expense);

    @Query("SELECT * FROM category_table")
    LiveData<List<Category>> getAllCategories();
}
