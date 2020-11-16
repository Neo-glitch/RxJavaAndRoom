package com.neo.roomrxjava.database.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "users")
public class User {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "userid")
    private String mId;

    @ColumnInfo(name = "username")
    private String mUserName;

    @ColumnInfo(name = "age")
    private String mAge;

    @ColumnInfo(name = "sex")
    private String mSex;

    @Ignore
    public User(String userName, String age, String sex) {
        mId = UUID.randomUUID().toString();
        mUserName = userName;
        mAge = age;
        mSex = sex;
    }

    public User(@NonNull String id, String userName, String age, String sex) {
        mId = id;
        mUserName = userName;
        mAge = age;
        mSex = sex;
    }

    @NonNull
    public String getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getAge() {
        return mAge;
    }

    public String getSex() {
        return mSex;
    }
}
