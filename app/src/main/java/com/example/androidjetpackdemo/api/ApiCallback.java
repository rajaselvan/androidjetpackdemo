package com.example.androidjetpackdemo.api;

import com.example.androidjetpackdemo.model.User;

import java.util.List;

public interface ApiCallback {

    void onSuccess(List<User> users);

    void onError(String errorMessage);
}
