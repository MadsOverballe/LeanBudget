package com.example.leanbudget.model;

import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyExchangeRates {
    private static CurrencyExchangeRates instance;

    private String base = "NOT LOADED";
    private String date = "";
    private double DKK = 0;

    private CurrencyExchangeRates() {
    }

    public static CurrencyExchangeRates getInstance() {
        if (instance == null) {
            instance = new CurrencyExchangeRates();
        }
        return instance;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDKK() {
        return DKK;
    }

    public void setDKK(double DKK) {
        this.DKK = DKK;
    }
}
