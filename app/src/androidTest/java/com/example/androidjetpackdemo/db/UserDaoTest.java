package com.example.androidjetpackdemo.db;

import android.support.test.runner.AndroidJUnit4;

import com.example.androidjetpackdemo.model.User;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest extends DbTest {

    @Test
    public void insertAndLoad() throws InterruptedException {
        List<User> userList = new ArrayList<>();
        final User user = new User(1, "Rajaselvan", "Thangaraj", "https://avatars3.githubusercontent.com/u/4468628?s=460&v=4",  new Date());
        userList.add(user);
        db.userDao().insertAll(userList);
    }
}