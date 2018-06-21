package com.example.androidjetpackdemo.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Webservice {
    @GET("users")
    Call<GetUsersListResponse> getUsers(@Query("page") int pageNumber);
}
