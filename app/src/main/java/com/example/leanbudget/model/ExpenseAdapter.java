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

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private static ExpenseAdapter instance;

    private ArrayList<Expense> expenses;

    private ExpenseAdapter() {
        this.expenses = new ArrayList<>();
    }

    public static ExpenseAdapter getInstance() {
        if (instance == null) {
            instance = new ExpenseAdapter();
        }
        return instance;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        this.notifyItemInserted(expenses.size() - 1);
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
}
