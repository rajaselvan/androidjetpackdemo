package com.example.androidjetpackdemo.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.androidjetpackdemo.App;
import com.example.androidjetpackdemo.model.User;
import com.example.androidjetpackdemo.utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebserviceClient {

    private static final String BASE_URL = "https://reqres.in/api/";


    public static Webservice create() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Webservice.class);
    }

    public static void fetchUsers(Webservice service, int page, final ApiCallback apiCallback) {
        if(NetworkUtils.isOnline()){
            service.getUsers(page).enqueue(new Callback<GetUsersListResponse>() {
                @Override
                public void onResponse(@Nullable Call<GetUsersListResponse> call, @NonNull Response<GetUsersListResponse> response) {
                    if (response.isSuccessful()) {
                        List<User> users;
                        if (response.body() != null) {
                            users = response.body().usersList;
                        } else {
                            users = new ArrayList<>();
                        }
                        apiCallback.onSuccess(users);
                    } else {
                        try {
                            apiCallback.onError(response.errorBody() != null ?
                                    response.errorBody().string() : "Unknown error");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<GetUsersListResponse> call, @NonNull Throwable t) {
                    apiCallback.onError(t.getMessage() != null ?
                            t.getMessage() : "Unknown error");
                }
            });
        } else {
            Toast.makeText(App.context, "Network not available", Toast.LENGTH_SHORT).show();
        }

    }
}
