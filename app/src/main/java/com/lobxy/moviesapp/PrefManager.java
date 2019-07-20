package com.lobxy.moviesapp;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private String preferenceType = "UserInterestPreference";

    Context context;

    public PrefManager(Context context) {
        this.context = context;
    }

    public void saveUserPreference(String typeChosen) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("preference", typeChosen);
        editor.apply();
    }

}
