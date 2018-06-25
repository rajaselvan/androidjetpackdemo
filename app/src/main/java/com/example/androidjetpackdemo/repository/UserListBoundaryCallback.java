package com.example.androidjetpackdemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.example.androidjetpackdemo.api.ApiCallback;
import com.example.androidjetpackdemo.api.Webservice;
import com.example.androidjetpackdemo.api.WebserviceClient;
import com.example.androidjetpackdemo.db.UserLocalCache;
import com.example.androidjetpackdemo.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used to signal when a PagedList has reached the end of available data.
 *
 * @author Rajaselvan
 */
public class UserListBoundaryCallback extends PagedList.BoundaryCallback<User> implements ApiCallback {

    private Webservice webservice;
    private UserLocalCache localCache;
    private int lastRequestedPage = 1;
    private boolean isRequestInProgress = false;
    private MutableLiveData<String> networkErrors = new MutableLiveData<>();

    UserListBoundaryCallback(Webservice webservice, UserLocalCache localCache) {
        this.webservice = webservice;
        this.localCache = localCache;
    }

    LiveData<String> getNetworkErrors() {
        return networkErrors;
    }


    private void requestAndSaveData() {
        if (isRequestInProgress) return;

        isRequestInProgress = true;

        WebserviceClient.fetchUsers(webservice, lastRequestedPage, this);
    }


    @Override
    public void onZeroItemsLoaded() {
        // Fetch next page from API when there is no data
        requestAndSaveData();
    }


    @Override
    public void onItemAtEndLoaded(@NonNull User itemAtEnd) {
        // Fetch next page from API when it is the end of the list
        requestAndSaveData();
    }


    @Override
    public void onSuccess(List<User> users) {

        // Add insert timestamp for refreshing our users
        List<User> timeStamped = new ArrayList<>();

        for(User user : users){
            user.setLastRefresh(new Date());
            timeStamped.add(user);
        }

        localCache.insert(users, () -> {
            lastRequestedPage++;
            isRequestInProgress = false;
        });
    }


    @Override
    public void onError(String errorMessage) {
        networkErrors.postValue(errorMessage);
        isRequestInProgress = false;
    }
}
