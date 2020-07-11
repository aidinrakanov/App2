package com.example.todo.data.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.todo.data.local.BoredDao;
import com.example.todo.model.BoredAction;

@Database(
        entities = {BoredAction.class},
        version = BoredDatabase.VERSION,
        exportSchema = false)

public abstract class BoredDatabase extends RoomDatabase {
    public  final static int VERSION = 1;
    public abstract BoredDao boredDao();


}
