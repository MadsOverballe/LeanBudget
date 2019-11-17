package com.example.leanbudget.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.CategoryList;
import com.example.leanbudget.model.Expense;
import com.example.leanbudget.model.ExpenseAdapter;
import com.example.leanbudget.R;

import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView expenseRecycler;
    private TextView totalBudgetValue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        expenseRecycler = root.findViewById(R.id.recycler_expenses);
        expenseRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        expenseRecycler.hasFixedSize();
        homeViewModel.getExpenses().observe(this, new Observer<ExpenseAdapter>() {
            @Override
            public void onChanged(@Nullable ExpenseAdapter s) {
                expenseRecycler.setAdapter(ExpenseAdapter.getInstance());
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor preferencesEditor = preferences.edit();
        totalBudgetValue = root.findViewById(R.id.total_budget_value);
        totalBudgetValue.setText(preferences.getString("totalBudget", "0"));

        totalBudgetValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                {
                    preferencesEditor.putString("totalBudget", s.toString());
                    preferencesEditor.apply();
                }
            }
        });


        // Test instantiations
        Category sportsCategory = new Category("Sports", R.drawable.ic_sport);
        Category electronicsCategory = new Category("Electronics", R.drawable.ic_electronics);
        Category foodCategory = new Category("Food", R.drawable.ic_cart);
        CategoryList.getInstance().add(sportsCategory);
        CategoryList.getInstance().add(electronicsCategory);
        CategoryList.getInstance().add(foodCategory);

        ExpenseAdapter.getInstance().addExpense(new Expense("Pool membership", 340, sportsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Batteries", 25, electronicsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Nintendo Switch", 2399, electronicsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Bike", 3225, sportsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Mouse", 299, electronicsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Burger", 50, foodCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Socks", 99, sportsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Adapter", 124.99, electronicsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Pizza", 75, foodCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Football", 299, sportsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Shoes", 699, sportsCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Pizza", 75, foodCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("Groceries", 212, foodCategory, new Date()));
        ExpenseAdapter.getInstance().addExpense(new Expense("HDMI cable", 49, electronicsCategory, new Date()));

        return root;
    }
}