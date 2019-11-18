package com.example.leanbudget.model;

import java.util.Dictionary;

public class CurrencyExchangeResponse {

    private String base;
    private String date;
    private Rates rates;

    public CurrencyExchangeRate getCurrencyExchangeRate() {
        return new CurrencyExchangeRate(base, date, rates.DKK);
    }

    private class Rates {
        private double DKK;
    }
}
