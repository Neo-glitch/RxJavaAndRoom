package com.neo.roomrxjava;


import com.neo.roomrxjava.database.model.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * interface method that's gonna be implemented by a type of dataSource
 * server or local dataSource
 */
public interface UserDataSource {

    // gets user from DataSource
    Observable<User> getUser();

    // inserts or updates user in db
    void insertOrUpdateUser(User user);

    // del all users from db
    void deleteAllUsers();
}
