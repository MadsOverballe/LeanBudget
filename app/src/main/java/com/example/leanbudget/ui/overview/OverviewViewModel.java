package com.example.leanbudget.ui.overview;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.leanbudget.model.Expense;
import com.example.leanbudget.model.ExpenseAdapter;
import com.example.leanbudget.database.BudgetDatabase;
import com.example.leanbudget.repository.Repository;

import java.util.List;

public class OverviewViewModel extends AndroidViewModel {

    private LiveData<List<Expense>> expenses;
    private Repository repository;

    public OverviewViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
        expenses = repository.getExpenses();
    }

    public LiveData<List<Expense>> getExpenses() {
        return expenses;
    }
}