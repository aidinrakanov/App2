package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String SETTINGS_SP = "isShown";
    static SharedPreferences sharedPreferences;
    static Activity activity;



    public SharedPrefs() {
        SharedPreferences preferences = activity.getSharedPreferences
                ("settings", Context.MODE_PRIVATE);

    }

    public void saveIsShown() {
        sharedPreferences.edit().putBoolean(SETTINGS_SP, true).apply();
    }

    public static boolean isShown() {
        return sharedPreferences.getBoolean
                (SETTINGS_SP, false);
    }
}
