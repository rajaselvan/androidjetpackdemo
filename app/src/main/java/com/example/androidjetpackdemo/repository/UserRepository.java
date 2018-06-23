package com.example.androidjetpackdemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.androidjetpackdemo.api.Webservice;
import com.example.androidjetpackdemo.db.UserLocalCache;
import com.example.androidjetpackdemo.model.GetUsersResult;
import com.example.androidjetpackdemo.model.User;


public class UserRepository {

    private static final int DATABASE_PAGE_SIZE = 3;
    private Webservice webservice;
    private UserLocalCache localCache;

    public UserRepository(Webservice webservice, UserLocalCache localCache) {
        this.webservice = webservice;
        this.localCache = localCache;
    }


    public GetUsersResult getUsers() {

        DataSource.Factory<Integer, User> usersList = localCache.getUsers();

        UserListBoundaryCallback boundaryCallback = new UserListBoundaryCallback(webservice, localCache);

        LiveData<String> networkErrors = boundaryCallback.getNetworkErrors();

        PagedList.Config pagedConfig = new PagedList.Config.Builder()
                .setPageSize(DATABASE_PAGE_SIZE)
                .build();

        LiveData<PagedList<User>> data = new LivePagedListBuilder<>(usersList, pagedConfig)
                .setBoundaryCallback(boundaryCallback)
                .build();

        return new GetUsersResult(data, networkErrors);
    }
}
