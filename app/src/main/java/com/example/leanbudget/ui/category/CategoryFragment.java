package com.example.leanbudget.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.leanbudget.R;
import com.example.leanbudget.model.Category;
import com.example.leanbudget.model.CategoryAdapter;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private CategoryViewModel categoryViewModel;
    private RecyclerView categoryRecycler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        categoryViewModel =
                ViewModelProviders.of(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        categoryRecycler = root.findViewById(R.id.categories_list);
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        categoryRecycler.hasFixedSize();
        categoryViewModel.getCategoryList().observe(this, new Observer<ArrayList<Category>>() {
            @Override
            public void onChanged(@Nullable ArrayList<Category> s) {
                categoryRecycler.setAdapter(CategoryAdapter.getInstance());
            }
        });

//        categoryViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
}