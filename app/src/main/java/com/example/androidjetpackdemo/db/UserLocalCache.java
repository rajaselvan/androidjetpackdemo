package com.example.androidjetpackdemo.db;

import android.arch.paging.DataSource;

import com.example.androidjetpackdemo.model.User;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

public class UserLocalCache {

    private UserDao mUserDao;

    private Executor ioExecutor;

    public UserLocalCache(UserDao userDao, Executor ioExecutor) {
        this.mUserDao = userDao;
        this.ioExecutor = ioExecutor;
    }


    public void insert(List<User> users, InsertCallback callback) {
        ioExecutor.execute(() -> {
            mUserDao.insertAll(users);
            callback.insertFinished();
        });
    }


    public DataSource.Factory<Integer, User> getUsers() {
        return mUserDao.getAllUsers();
    }

    public List<User> getOutdatedUsers(Date lastRefreshMax){
        return mUserDao.getOutdatedUsers(lastRefreshMax);
    }

    public void clearCache(){
        ioExecutor.execute( () -> {
            mUserDao.deleteAll();
        });
    }
}
