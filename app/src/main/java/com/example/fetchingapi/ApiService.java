package com.example.fetchingapi;

import com.example.fetchingapi.model.DataResponse;
import com.example.fetchingapi.model.UsersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/users")
    Call<UsersResponse> getUsers(@Query("page") String page);

    @GET("api/users/{id}")
    Call<DataResponse> getUserById(@Path("id") String id);

}
