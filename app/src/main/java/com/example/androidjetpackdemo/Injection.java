package com.example.androidjetpackdemo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.androidjetpackdemo.api.WebserviceClient;
import com.example.androidjetpackdemo.db.UserDataBase;
import com.example.androidjetpackdemo.db.UserLocalCache;
import com.example.androidjetpackdemo.repository.UserRepository;
import com.example.androidjetpackdemo.ui.ViewModelFactory;

import java.util.concurrent.Executors;

public class Injection {

    @NonNull
    private static UserLocalCache provideCache(Context context) {
        UserDataBase userDatabase = UserDataBase.getInstance(context);
        return new UserLocalCache(userDatabase.userDao(), Executors.newSingleThreadExecutor());
    }


    @NonNull
    private static UserRepository provideWebSeriveClient(Context context) {
        return new UserRepository(WebserviceClient.create(), provideCache(context));
    }


    @NonNull
    public static ViewModelFactory provideViewModelFactory(Context context) {
        return new ViewModelFactory(provideWebSeriveClient(context));
    }
}
