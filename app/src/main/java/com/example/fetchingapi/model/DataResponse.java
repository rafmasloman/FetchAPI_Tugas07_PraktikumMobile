package com.example.fetchingapi.model;

import com.google.gson.annotations.SerializedName;

public class DataResponse {
    @SerializedName("data")
    private UserResponse data;

    public UserResponse getData() {
        return data;
    }

}
