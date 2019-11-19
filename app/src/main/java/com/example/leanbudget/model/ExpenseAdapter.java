package com.example.leanbudget.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leanbudget.R;

import java.util.ArrayList;
import java.util.Date;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private static ExpenseAdapter instance;

    private ArrayList<Expense> expenses;

    private ExpenseAdapter() {
        this.expenses = new ArrayList<>();
    }

    public static ExpenseAdapter getInstance() {
        if (instance == null) {
            instance = new ExpenseAdapter();
            mockData();
        }
        return instance;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        this.notifyItemInserted(expenses.size() - 1);
    }

    public ExpenseAdapter getExpenseAdapter(Category category) {
        ExpenseAdapter adapter = new ExpenseAdapter();
        ArrayList<Expense> result = new ArrayList<>();
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            if (expense.getCategory().getName().equals(category.getName())){
                result.add(expense);
            }
        }
        adapter.expenses = result;
        return adapter;
    }

    @NonNull
    @Override
    public ExpenseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.expense_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ViewHolder holder, int position) {
        Expense expense = expenses.get(position);
        holder.categoryImage.setImageResource(expense.getCategory().getIconId());
        holder.description.setText(expense.getDescription());
        holder.cost.setText(String.valueOf(expense.getCost()));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView description;
        TextView cost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.expense_container_category_image);
            description = itemView.findViewById(R.id.expense_container_description);
            cost = itemView.findViewById(R.id.expense_container_cost);
        }
    }

    private static void mockData() {
        CategoryList.getInstance();
        Category sportsCategory = CategoryList.getCategory("Sports");
        Category electronicsCategory = CategoryList.getCategory("Electronics");
        Category foodCategory = CategoryList.getCategory("Food");

        instance.addExpense(new Expense("Pool membership", 340, sportsCategory, new Date()));
        instance.addExpense(new Expense("Batteries", 25, electronicsCategory, new Date()));
        instance.addExpense(new Expense("Nintendo Switch", 2399, electronicsCategory, new Date()));
        instance.addExpense(new Expense("Bike", 3225, sportsCategory, new Date()));
        instance.addExpense(new Expense("Mouse", 299, electronicsCategory, new Date()));
        instance.addExpense(new Expense("Burger", 50, foodCategory, new Date()));
        instance.addExpense(new Expense("Socks", 99, sportsCategory, new Date()));
        instance.addExpense(new Expense("Adapter", 124.99, electronicsCategory, new Date()));
        instance.addExpense(new Expense("Pizza", 75, foodCategory, new Date()));
        instance.addExpense(new Expense("Football", 299, sportsCategory, new Date()));
        instance.addExpense(new Expense("Shoes", 699, sportsCategory, new Date()));
        instance.addExpense(new Expense("Pizza", 75, foodCategory, new Date()));
        instance.addExpense(new Expense("Groceries", 212, foodCategory, new Date()));
        instance.addExpense(new Expense("HDMI cable", 49, electronicsCategory, new Date()));
    }
}
