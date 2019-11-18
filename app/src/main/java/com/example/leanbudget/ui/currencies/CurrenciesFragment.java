package com.example.leanbudget.ui.currencies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.leanbudget.R;

public class CurrenciesFragment extends Fragment {

    private CurrenciesViewModel currenciesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        currenciesViewModel =
                ViewModelProviders.of(this).get(CurrenciesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_currencies, container, false);
        final TextView base = root.findViewById(R.id.currencies_base_value);
        final TextView dkk = root.findViewById(R.id.currencies_dkk_value);
        currenciesViewModel.getBase().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                base.setText(s);
            }
        });
        currenciesViewModel.getDkk().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                dkk.setText(s);
            }
        });
        return root;
    }
}