package com.deepak.indianews;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInformation {
    public static final String URL ="https://newsapi.org/v2/";
    private static Retrofit retrofit =null;
    public static Retrofit getRetrofit(){
        if (retrofit==null){
            retrofit  = new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
