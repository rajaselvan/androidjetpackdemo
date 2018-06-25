package com.example.androidjetpackdemo.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.androidjetpackdemo.model.User;

import java.util.Date;
import java.util.List;


/**
 * Room data access object for accessing users table.
 *
 * @author Rajaselvan
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * FROM users ORDER BY id ASC")
    DataSource.Factory<Integer, User> getAllUsers();

    @Query("SELECT * FROM users WHERE last_refresh < :lastRefreshMax LIMIT 1" )
    User hasOutdatedUsers(Date lastRefreshMax);
}
