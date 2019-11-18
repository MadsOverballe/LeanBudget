package com.example.leanbudget.model;

public class CurrencyExchangeRate {
    private String base;
    private String date;
    private double DKK;

    public CurrencyExchangeRate(String base, String date, double DKK)
    {
        this.base = base;
        this.date = date;
        this.DKK = DKK;
    }

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public double getDKK() {
        return DKK;
    }
}
