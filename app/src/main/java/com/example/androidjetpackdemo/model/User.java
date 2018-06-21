package com.example.androidjetpackdemo.model;


import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    public Integer id;

    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("avatar")
    public String avatar;

}
