package com.neo.roomrxjava.ui;

import androidx.lifecycle.ViewModel;

import com.neo.roomrxjava.UserDataSource;
import com.neo.roomrxjava.database.model.User;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public class MainActivityViewModel extends ViewModel {

    private final UserDataSource mDataSource;
    private User mUser;

    public MainActivityViewModel(UserDataSource dataSource) {
        mDataSource = dataSource;
    }

    /*
    gets userName of user
    ret Flowable/ observable that will emit when userName is updated
     */
    public Observable<String> getUserName(){
        return mDataSource.getUser()
                .map(user -> {
                    mUser = user;
                    return user.getUserName();
                });
    }

    public Observable<String> getUserAge(){
        return mDataSource.getUser()
                .map(user -> {
                    mUser = user;
                    return user.getAge();
                });
    }

    public Observable<String> getUserSex(){
        return mDataSource.getUser()
                .map(user -> {
                    mUser = user;
                    return user.getSex();
                });
    }

    /*
    Updates the userName with new userName
    Completable means emits only when task is completed
     */
    public Completable updateUserName(final String userName, String userAge, String userSex){
        return Completable.fromAction(() -> {
            // if no user create new user, else create new user with id of previous user with updated user info
            mUser = mUser == null
                    ? new User(userName, userAge, userSex) :new User(mUser.getId(), userName, userAge, userSex);

            mDataSource.insertOrUpdateUser(mUser);
        });
    }
}
