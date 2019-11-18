package com.example.leanbudget.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CurrencyExchangeServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static CurrencyExchangeApi currencyExchangeApi = retrofit.create(CurrencyExchangeApi.class);

    public static CurrencyExchangeApi getCurrencyExchangeApi() {
        return currencyExchangeApi;
    }
}
