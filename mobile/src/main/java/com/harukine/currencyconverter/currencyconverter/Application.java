package com.harukine.currencyconverter.currencyconverter;


import org.androidannotations.annotations.EApplication;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

@EApplication
public class Application extends android.app.Application {

    public APIService service;

    public Application() {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint("http://api.exchangeratelab.com/api/")
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request){
                        request.addQueryParam("apikey", "2C5F64E8B7B1073C43EBC50BAE01A587");
                    }


                }).build();

        service = adapter.create(APIService.class);
    }


}