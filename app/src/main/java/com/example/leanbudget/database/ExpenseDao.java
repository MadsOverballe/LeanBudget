package com.example.leanbudget.database;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import com.example.leanbudget.model.Expense;

import java.util.List;

@Dao
public interface ExpenseDao {
   @Insert
    void insert(Expense expense);

   @Update
    void update(Expense expense);

   @Delete
    void delete(Expense expense);

   @Query("SELECT * FROM expense_table")
   LiveData<List<Expense>> getAllExpenses();
}
