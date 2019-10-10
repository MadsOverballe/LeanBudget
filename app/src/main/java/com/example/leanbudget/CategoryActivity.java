package com.example.leanbudget;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.CategoryList;
import com.example.leanbudget.model.Expense;
import com.example.leanbudget.model.ExpenseAdapter;

import java.util.Date;

public class CategoryActivity extends AppCompatActivity {

    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        name = findViewById(R.id.category_add_name_input);
    }

    public void addClicked(View view) {
        try {
            // Mocks
            int iconPlaceholder = R.drawable.ic_money;

            String nameValue = name.getText().toString();
            if (name.equals("")) {
                throw new IllegalArgumentException("Name cannot be empty");
            }

            Category category = new Category(nameValue, iconPlaceholder);

            CategoryList.getInstance().add(category);
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
