package com.example.androidjetpackdemo.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.example.androidjetpackdemo.model.User;
import com.example.androidjetpackdemo.utils.DateConverter;

@Database(entities = {User.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class UserDataBase extends RoomDatabase {

    private static final String DB_NAME = "userDatabase.db";
    private static volatile UserDataBase instance;

    public static synchronized UserDataBase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static UserDataBase create(final Context context) {
        return Room.databaseBuilder(
                context,
                UserDataBase.class,
                DB_NAME).build();
    }

    public abstract UserDao userDao();
}
