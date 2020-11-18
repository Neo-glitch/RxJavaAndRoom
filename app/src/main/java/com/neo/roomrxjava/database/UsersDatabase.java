package com.neo.roomrxjava.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.neo.roomrxjava.database.model.User;


@Database(entities = {User.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {
    private static volatile UsersDatabase INSTANCE;
    // to access dao obj
    public abstract UserDao mUserDao();

    public synchronized static UsersDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    UsersDatabase.class, "Users.db").build();
        }
        return INSTANCE;
    }

}
