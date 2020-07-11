package com.example.todo;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static final String SETTINGS_SP = "isShown";
    static SharedPreferences sharedPreferences;


//    public SharedPrefs(Context context) {
//        sharedPreferences = context.getSharedPreferences
//                ("settings", Context.MODE_PRIVATE);
//
//    }

    public void saveIsShown() {
        sharedPreferences.edit().putBoolean(SETTINGS_SP, false).apply();
    }

    public boolean isShown() {
        return sharedPreferences.getBoolean(SETTINGS_SP, true);
    }
}
