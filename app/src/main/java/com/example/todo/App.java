package com.example.todo;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {
    public  static  SharedPrefs prefs;

    @Override
    public void onCreate() {
        prefs = new SharedPrefs(this);
        super.onCreate();
    }

}
