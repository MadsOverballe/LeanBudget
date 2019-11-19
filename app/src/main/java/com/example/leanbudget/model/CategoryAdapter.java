package com.example.leanbudget.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leanbudget.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private static CategoryAdapter instance;

    private ArrayList<Category> categories;
    private ArrayList<Expense> expenses;

    private CategoryAdapter() {
        this.categories = new ArrayList<>();
    }

    public static CategoryAdapter getInstance() {
        if (instance == null) {
            instance = new CategoryAdapter();
            instance.categories = CategoryList.getInstance();
        }
        return instance;
    }

    public void addCategory(Category category) {
        categories.add(category);
        this.notifyItemInserted(categories.size() - 1);
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryImage.setImageResource(category.getIconId());
        holder.description.setText(category.getName());
        holder.expenses.setAdapter(ExpenseAdapter.getInstance().getExpenseAdapter(category));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView description;
        RecyclerView expenses;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage = itemView.findViewById(R.id.category_container_category_image);
            description = itemView.findViewById(R.id.category_container_description);
            expenses = itemView.findViewById(R.id.category_expense_list);
            expenses.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            expenses.hasFixedSize();
        }
    }
}
