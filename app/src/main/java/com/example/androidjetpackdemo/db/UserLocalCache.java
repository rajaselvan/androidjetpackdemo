package com.example.androidjetpackdemo.db;

import android.arch.paging.DataSource;

import com.example.androidjetpackdemo.model.User;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * This class handles all our database operations.
 *
 * @author Rajaselvan
 */
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
            if (null != callback) {
                callback.insertFinished();
            }
        });
    }


    public DataSource.Factory<Integer, User> getUsers() {
        return mUserDao.getAllUsers();
    }

    public User hasOutdatedUsers(Date lastRefreshMax) {
        return mUserDao.hasOutdatedUsers(lastRefreshMax);
    }

    public void clearCache() {
        ioExecutor.execute(() -> {
            mUserDao.deleteAll();
        });
    }
}
