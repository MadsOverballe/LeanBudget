package com.example.leanbudget.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.leanbudget.database.BudgetDatabase;
import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.Expense;

import java.util.List;

public class Repository {
    private static Repository instance;
    private BudgetDatabase database;

    private Repository(Application application) {
        database = BudgetDatabase.getInstance(application.getApplicationContext());
    }

    public static Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }

//    public LiveData<List<Expense>> getExpenses() {
//        return database.expenseDao().getAllExpenses();
//    }

}
