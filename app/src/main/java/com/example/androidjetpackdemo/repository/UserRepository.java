package com.example.androidjetpackdemo.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.androidjetpackdemo.db.UserDao;
import com.example.androidjetpackdemo.db.UserDataBase;
import com.example.androidjetpackdemo.model.User;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mUsers;

    UserRepository(Application application) {
        UserDataBase db = UserDataBase.getInstance(application);
        mUserDao = db.userDao();
        mUsers = mUserDao.getAllUsers();
    }

    LiveData<List<User>> getAllUsers() {
        return mUsers;
    }


    public void insertUsers (List<User> users) {
        new insertAsyncTask(mUserDao).execute(users);
    }

    private static class insertAsyncTask extends AsyncTask<List<User>, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<User>... params) {
            mAsyncTaskDao.insertAll(params[0]);
            return null;
        }
    }
}
