package com.example.androidjetpackdemo.model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @SerializedName("id")
    public Integer id;

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    public String lastName;

    @SerializedName("avatar")
    public String avatar;

}
