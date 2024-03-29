package com.example.leanbudget;

import android.content.Intent;
import android.os.Bundle;

import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.CategoryList;
import com.example.leanbudget.model.CurrencyExchangeApi;
import com.example.leanbudget.model.CurrencyExchangeRates;
import com.example.leanbudget.model.CurrencyExchangeResponse;
import com.example.leanbudget.model.CurrencyExchangeServiceGenerator;
import com.example.leanbudget.model.Expense;
import com.example.leanbudget.model.ExpenseAdapter;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    // Authentication
    private static final int RC_SIGN_IN = 123;
    private FirebaseUser user;

    // Floating action button
    private FloatingActionButton fab_main;
    private FloatingActionButton fab1_expense;
    private FloatingActionButton fab2_category;
    private Animation fab_open;
    private Animation fab_close;
    private Animation fab_clock;
    private Animation fab_anticlock;
    private TextView text_expense;
    private TextView text_category;
    private boolean isOpen = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Mock data
        //mockCategoriesAndExpenses();

        // Authentication
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null){
            Toast.makeText(this, "Signed into: " + user.getEmail(), Toast.LENGTH_LONG).show();
        }
        else {
            signIn(null);
        }

        // Floating action button
        fab_main = findViewById(R.id.fab);
        fab1_expense = findViewById(R.id.fab1);
        fab2_category = findViewById(R.id.fab2);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_clock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_clock);
        fab_anticlock = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_rotate_anticlock);
        text_expense = findViewById(R.id.fab_text_expense);
        text_category = findViewById(R.id.fab_text_category);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_category, R.id.nav_currencies)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void signIn(View v) {
        List<AuthUI.IdpConfig> providers =
                Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.ic_money)
                .build();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void signOut(View v) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        signIn(null);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void fabClicked(View view)
    {
        if (isOpen) {
            fabClose();
        } else {
            fabOpen();
        }
    }

    private void fabOpen() {
        text_expense.setVisibility(View.VISIBLE);
        text_category.setVisibility(View.VISIBLE);
        fab1_expense.startAnimation(fab_open);
        fab2_category.startAnimation(fab_open);
        fab_main.startAnimation(fab_clock);
        fab1_expense.setClickable(true);
        fab2_category.setClickable(true);
        isOpen = true;
    }

    private void fabClose() {
        text_expense.setVisibility(View.INVISIBLE);
        text_category.setVisibility(View.INVISIBLE);
        fab1_expense.startAnimation(fab_close);
        fab2_category.startAnimation(fab_close);
        fab_main.startAnimation(fab_anticlock);
        fab1_expense.setClickable(false);
        fab2_category.setClickable(false);
        isOpen = false;
    }

    public void fabExpenseClicked(View view) {
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
        fabClose();
    }

    public void fabCategoryClicked(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
        fabClose();
    }

    public void signOutClicked(MenuItem item) {
        signOut(null);
    }

    public void loadButtonClicked(View view) {
        requestCurrencyExchangeRates();
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    // Currencies
    public void requestCurrencyExchangeRates() {
        CurrencyExchangeApi currencyExchangeApi = CurrencyExchangeServiceGenerator.getCurrencyExchangeApi();
        Call<CurrencyExchangeResponse> call = currencyExchangeApi.getCurrencyExchangeRates();
        call.enqueue(new Callback<CurrencyExchangeResponse>() {
            @Override
            public void onResponse(Call<CurrencyExchangeResponse> call, Response<CurrencyExchangeResponse> response) {
                if (response.code() == 200) {
                    CurrencyExchangeRates currencyExchangeRate = response.body().getCurrencyExchangeRate();
                }
            }
            @Override
            public void onFailure(Call<CurrencyExchangeResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    private void mockCategoriesAndExpenses() {
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
    }

}
