package com.lobxy.moviesapp.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Users.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UsersDao getDAO();

}

