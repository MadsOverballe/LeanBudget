package com.example.leanbudget.model;

public class CurrencyExchangeResponse {

    private String base;
    private String date;
    private Rates rates;

    public CurrencyExchangeRates getCurrencyExchangeRate() {
        CurrencyExchangeRates exchangeRates = CurrencyExchangeRates.getInstance();
        exchangeRates.setBase(base);
        exchangeRates.setDate(date);
        exchangeRates.setDKK(rates.DKK);
        exchangeRates.setUSD(rates.USD);
        return exchangeRates;
    }

    private class Rates {
        private double DKK;
        private double USD;
    }
}
