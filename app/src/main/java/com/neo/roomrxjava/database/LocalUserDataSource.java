package com.neo.roomrxjava.database;

import com.neo.roomrxjava.UserDataSource;
import com.neo.roomrxjava.database.model.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;


/*
kinda acts like a repo
 */
public class LocalUserDataSource implements UserDataSource {
    private final UserDao mUserDao;

    public LocalUserDataSource(UserDao userDao){
        mUserDao = userDao;
    }

    @Override
    public Observable<User> getUser() {
        return mUserDao.getUser();
    }

    @Override
    public void insertOrUpdateUser(User user) {
        mUserDao.insertUser(user);
    }

    @Override
    public void deleteAllUsers() {
        mUserDao.deleteAllUsers();
    }
}
