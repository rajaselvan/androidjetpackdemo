package com.example.androidjetpackdemo.model;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

/**
 * This class contains LiveData for both our data and network status
 *
 * @author Rajaselvan
 */
public class GetUsersResult {

    private final LiveData<PagedList<User>> data;
    private final LiveData<String> networkErrors;

    public GetUsersResult(LiveData<PagedList<User>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    public LiveData<PagedList<User>> getData() {
        return data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }
}
