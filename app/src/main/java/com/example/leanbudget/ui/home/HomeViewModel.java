package com.example.leanbudget.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leanbudget.model.ExpenseAdapter;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<ExpenseAdapter> expenses;

    public HomeViewModel() {
        expenses = new MutableLiveData<>();
        expenses.setValue(ExpenseAdapter.getInstance());
    }

    public LiveData<ExpenseAdapter> getExpenses() {
        return expenses;
    }
}