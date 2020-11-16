package com.neo.roomrxjava.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.neo.roomrxjava.database.model.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;


@Dao
public interface UserDao {

    /*
    gets all user from db, put limit res to just first user
     */
    @Query("SELECT * FROM Users LIMIT 1")
    Observable<User> getUser();

    /*
    Insert user in db, if user already exists replace it
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    /*
    deletes all users
     */
    @Query("DELETE FROM Users")
    void deleteAllUsers();
}
