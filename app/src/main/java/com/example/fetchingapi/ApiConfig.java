package com.example.fetchingapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {
    private static String URL_API = "https://reqres.in/";

    public static ApiService getApiService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_API).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(ApiService.class);
    }

}
