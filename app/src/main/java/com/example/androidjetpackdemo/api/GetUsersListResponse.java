package com.example.androidjetpackdemo.api;

import com.example.androidjetpackdemo.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Sample User API response model.
 *
 * @author Rajaselvan
 */
public class GetUsersListResponse {

    @SerializedName("page")
    public Integer page;

    @SerializedName("per_page")
    public Integer perPage;

    @SerializedName("total")
    public Integer total;

    @SerializedName("total_pages")
    public Integer totalPages;

    @SerializedName("data")
    public List<User> usersList = null;

}
