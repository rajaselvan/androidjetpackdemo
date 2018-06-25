package com.example.androidjetpackdemo.api;

import com.example.androidjetpackdemo.model.User;

import java.util.List;

/**
 * Interface for passing data between WebserviceClient
 * and UserListBoundaryCallback.
 *
 * @author Rajaselvan
 */
public interface ApiCallback {

    void onSuccess(List<User> users);

    void onError(String errorMessage);
}
