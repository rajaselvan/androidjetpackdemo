package com.example.androidjetpackdemo.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.androidjetpackdemo.model.User;

import java.util.List;

public class ListViewModel extends ViewModel {
    private LiveData<List<User>> users;
    public LiveData<List<User>> getUsers() {
        return users;
    }
}
