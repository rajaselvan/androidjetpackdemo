package com.example.androidjetpackdemo;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.androidjetpackdemo.api.WebserviceClient;
import com.example.androidjetpackdemo.db.UserDataBase;
import com.example.androidjetpackdemo.db.UserLocalCache;
import com.example.androidjetpackdemo.repository.UserRepository;
import com.example.androidjetpackdemo.ui.ViewModelFactory;

import java.util.concurrent.Executors;

/**
 * This class handles object creation.
 *
 * @author Rajaselvan
 */
public class Injection {

    @NonNull
    public static UserLocalCache provideCache(Context context) {
        UserDataBase userDatabase = UserDataBase.getInstance(context);
        return new UserLocalCache(userDatabase.userDao(), Executors.newSingleThreadExecutor());
    }


    @NonNull
    public static UserRepository provideWebSeriveClient(Context context) {
        return new UserRepository(WebserviceClient.create(), provideCache(context), Executors.newSingleThreadExecutor());
    }


    @NonNull
    public static ViewModelFactory provideViewModelFactory(Context context) {
        return new ViewModelFactory(provideWebSeriveClient(context));
    }
}
