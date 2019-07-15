package com.lobxy.moviesapp.Room;

import androidx.room.Room;
import android.content.Context;
import android.os.AsyncTask;

public class UsersRepository {
    private final String DB_NAME = "User.db";

    public static UserDatabase userDatabase;

    public UsersRepository(Context context) {
        userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
    }

    public void addUser(String username, String password) {
        final Users users = new Users(username, password);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDatabase.getDAO().insertUser(users);
                return null;
            }
        }.execute();
    }

    public boolean userExists(final String username) {
        return userDatabase.getDAO().userexists(username);
    }

    public boolean loginUser(String username, String password) {
        return userDatabase.getDAO().loginUser(username, password);
    }
}



