package com.example.leanbudget;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

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
                R.id.nav_overview, R.id.nav_categories, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
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
}
