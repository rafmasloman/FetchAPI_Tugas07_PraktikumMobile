package com.example.fetchingapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UsersResponse {

    @SerializedName("data")
    private List<UserResponse> data;
    public List<UserResponse> getData() {
        return data;
    }
}
