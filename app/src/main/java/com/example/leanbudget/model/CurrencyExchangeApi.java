package com.example.leanbudget.model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyExchangeApi {

    @GET("latest")
    Call<CurrencyExchangeResponse> getCurrencyExchangeRates();
}
