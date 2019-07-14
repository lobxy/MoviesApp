package com.lobxy.moviesapp.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface UsersDao {

    @Insert
    long insertUser(Users users);

    @Query("select * from users where username = :user")
    boolean userexists(String user);

    @Query("select * from users where username = :user & password = :pwd")
    boolean loginUser(String user, String pwd);

}
