package com.example.simplenews.repositories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
* Creating a single retrofit client for the app (Singleton pattern)
* so that we don't spawn multiple clients wasting devices
* resources.
* */
public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String baseUrl) {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
