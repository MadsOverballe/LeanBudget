package com.example.leanbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.CategoryList;
import com.example.leanbudget.model.Expense;
import com.example.leanbudget.model.ExpenseAdapter;

import java.util.Date;

public class ExpenseActivity extends AppCompatActivity {

    private EditText description;
    private EditText cost;
    private Spinner category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        description = findViewById(R.id.expense_add_description_input);
        cost = findViewById(R.id.expense_add_cost_input);
        category = findViewById(R.id.expense_add_category_spinner);

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, CategoryList.getInstance());
        category.setAdapter(categoryAdapter);
    }

    public void addClicked(View view) {
        try {
            // Mocks
            Category placeholderCategory = new Category("Default", R.drawable.ic_money);
            Date placeholderDatetime = new Date();

            Category categoryValue = (Category) category.getSelectedItem();
            String descriptionValue = description.getText().toString();
            if (descriptionValue.equals("")) {
                throw new IllegalArgumentException("Description cannot be empty");
            }
            double costValue;
            try {
                costValue = Double.parseDouble(cost.getText().toString());
            }
            catch (Exception exception) {
                throw new IllegalArgumentException("Cost cannot be empty");
            }

            Expense expense = new Expense(descriptionValue, costValue, categoryValue, placeholderDatetime);

            ExpenseAdapter.getInstance().addExpense(expense);
            finish();
        }
        catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelClicked(View view) {
        finish();
    }
}
