package com.example.androidjetpackdemo.api;

import com.example.androidjetpackdemo.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUsersListResponse {

    @SerializedName("page")
    private Integer page;

    @SerializedName("per_page")
    private Integer perPage;

    @SerializedName("total")
    private Integer total;

    @SerializedName("total_pages")
    private Integer totalPages;

    @SerializedName("data")
    private List<User> usersList = null;

}
