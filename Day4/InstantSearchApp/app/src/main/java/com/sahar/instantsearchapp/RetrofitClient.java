package com.sahar.instantsearchapp;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public  final String BASE_URL = "https://api.androidhive.info";

    public RetrofitClient() {

    }

    private Retrofit getRetrofitInstance() {

        return new Retrofit.Builder()
                .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public  RetrofitInterface getRetrofitService(){
        return getRetrofitInstance().create(RetrofitInterface.class);
    }
}
