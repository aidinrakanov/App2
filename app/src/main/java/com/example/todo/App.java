package com.example.todo;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.todo.data.BoredApiClient;
import com.example.todo.model.BoredAction;

public class App extends Application {
    public  static  SharedPrefs prefs;
    public static BoredApiClient boredApiClient;


    @Override
    public void onCreate() {
        prefs = new SharedPrefs(this);
        boredApiClient = new BoredApiClient();
        super.onCreate();
    }

}
