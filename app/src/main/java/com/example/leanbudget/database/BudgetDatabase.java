package com.example.leanbudget.database;

import android.content.Context;

import androidx.room.*;

import com.example.leanbudget.model.*;

@Database(entities = {Expense.class, Category.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class BudgetDatabase extends RoomDatabase {

    private static BudgetDatabase instance;
    public abstract ExpenseDao expenseDao();
    public abstract CategoryDao categoryDao();

    public static synchronized BudgetDatabase getInstance(Context context){
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BudgetDatabase.class, "database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
