package com.example.fetchingapi.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("last_name")

    private  String lastName;

    @SerializedName("email")
    private String email;


    @SerializedName("avatar")
    private String avatarImg;

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

}
