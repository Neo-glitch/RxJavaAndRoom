package com.neo.roomrxjava;


import android.content.Context;
import android.view.View;

import com.neo.roomrxjava.database.LocalUserDataSource;
import com.neo.roomrxjava.database.UsersDatabase;
import com.neo.roomrxjava.ui.MainActivityViewModelFactory;

/*
Enables data injection of data Sources
 */
public class Injection {

    public static UserDataSource provideUserDataSource(Context context){
        UsersDatabase database = UsersDatabase.getInstance(context);
        return new LocalUserDataSource(database.mUserDao());
    }

    public static MainActivityViewModelFactory provideViewModelFactory(Context context){
        UserDataSource dataSource = provideUserDataSource(context);
        return new MainActivityViewModelFactory(dataSource);
    }
}
