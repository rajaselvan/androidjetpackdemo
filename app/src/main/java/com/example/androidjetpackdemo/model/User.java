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

    public User(Integer id, String firstName, String lastName, String avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
