package com.example.androidjetpackdemo.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.androidjetpackdemo.model.User;

import java.util.List;



@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Query("SELECT * FROM users ORDER BY id ASC")
    DataSource.Factory<Integer, User> getAllUsers();
}
