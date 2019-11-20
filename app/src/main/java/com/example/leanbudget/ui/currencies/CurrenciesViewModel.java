package com.example.leanbudget.ui.currencies;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.leanbudget.model.CurrencyExchangeApi;
import com.example.leanbudget.model.CurrencyExchangeRates;
import com.example.leanbudget.model.CurrencyExchangeResponse;
import com.example.leanbudget.model.CurrencyExchangeServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrenciesViewModel extends ViewModel {

    private MutableLiveData<String> base;
    private MutableLiveData<String> dkk;
    private MutableLiveData<String> usd;

    public CurrenciesViewModel() {
        //requestCurrencyExchangeRates();

        base = new MutableLiveData<>();
        dkk = new MutableLiveData<>();
        usd = new MutableLiveData<>();

        CurrencyExchangeRates currencyExchangeRates = CurrencyExchangeRates.getInstance();
        base.setValue(currencyExchangeRates.getBase());
        dkk.setValue(Double.toString(currencyExchangeRates.getDKK()));
        usd.setValue(Double.toString(currencyExchangeRates.getUSD()));
    }

    public LiveData<String> getBase() {
        return base;
    }

    public LiveData<String> getDkk() {
        return dkk;
    }

    public LiveData<String> getUsd() {
        return usd;
    }
}