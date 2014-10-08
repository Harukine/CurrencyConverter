package com.harukine.currencyconverter.currencyconverter;


import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface APIService {

    @GET("/current/{base}")
    void convertCurrentCurrency(
            @Path("base") String baseCurrency,
            Callback<CurrencyRates> response);
}
