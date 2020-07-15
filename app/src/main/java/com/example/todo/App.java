package com.example.todo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.todo.data.BoredApiClient;
import com.example.todo.data.db.BoredDatabase;
import com.example.todo.data.local.BoredStorage;
import com.example.todo.model.BoredAction;

public class App extends Application {
    public  static  SharedPrefs prefs;
    public static BoredApiClient boredApiClient;
    private static BoredDatabase boredDatabase;
    public static BoredStorage boredStorage;
    public static App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        prefs = new SharedPrefs(this);
        boredApiClient = new BoredApiClient();
        instance = this;
        boredDatabase = Room.databaseBuilder(
                        this,
                        BoredDatabase.class,
                        "bored.db")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build();

        boredStorage = new BoredStorage(boredDatabase.boredDao());
            }

    public static App getInstance() {
        return instance;
    }

    public BoredDatabase getDatabase() {
        return boredDatabase;
    }


    }
