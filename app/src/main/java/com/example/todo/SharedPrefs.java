package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    static SharedPreferences sharedPreferences;
    static Activity activity;



    public SharedPrefs() {
        SharedPreferences preferences = activity.getSharedPreferences
                ("settings", Context.MODE_PRIVATE);

    }

    public void saveIsShown() {
        sharedPreferences.edit().putBoolean("isShown", true).apply();
    }

    public static boolean isShown() {
        return sharedPreferences.getBoolean
                ("isShown", false);
    }
}
