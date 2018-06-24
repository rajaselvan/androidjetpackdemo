package com.example.androidjetpackdemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;


import com.example.androidjetpackdemo.api.Webservice;
import com.example.androidjetpackdemo.db.UserLocalCache;
import com.example.androidjetpackdemo.model.GetUsersResult;
import com.example.androidjetpackdemo.model.User;
import com.example.androidjetpackdemo.utils.NetworkUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;


public class UserRepository {

    private static final int DATABASE_PAGE_SIZE = 3;
    private Webservice webservice;
    private UserLocalCache localCache;
    private Executor executor;
    private static int REFRESH_TIMEOUT_IN_MINUTES = 2;


    public UserRepository(Webservice webservice, UserLocalCache localCache, Executor executor) {
        this.webservice = webservice;
        this.localCache = localCache;
        this.executor = executor;
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

        refreshUsers();

        return new GetUsersResult(data, networkErrors);
    }

    private void refreshUsers() {
        executor.execute(() -> {
            boolean needsRefresh = localCache.getOutdatedUsers(getRefreshTimeOut(new Date())).size() > 0 ;
            if(needsRefresh) {
                if(NetworkUtils.isOnline()){
                    localCache.clearCache();
                }
            }
        });
    }

    private Date getRefreshTimeOut(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -REFRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
