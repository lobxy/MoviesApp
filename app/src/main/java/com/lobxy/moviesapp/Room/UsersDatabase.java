package com.lobxy.moviesapp.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;

@Database(entities = {Users.class}, version = 1)
public abstract class UsersDatabase extends RoomDatabase {

    private final String DB_NAME = "Users.db";

    public static UsersDatabase instance;

    public abstract UsersDao getDao();

    public UsersDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, UsersDatabase.class, DB_NAME).build();
        }
    }

    public void addUser(String username, String password) {
        final Users users = new Users(username, password);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                instance.getDao().insertUser(users);
                return null;
            }
        }.execute();
    }

    public boolean userExists(final String username) {
        return instance.getDao().userexists(username);
    }

    public boolean loginUser(String username, String password) {
        return instance.getDao().loginUser(username, password);
    }


}
