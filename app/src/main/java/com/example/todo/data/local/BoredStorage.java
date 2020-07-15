package com.example.todo.data.local;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.todo.model.BoredAction;

import java.util.List;

public class BoredStorage {

    private BoredDao dao;

    public BoredStorage(BoredDao dao){
        this.dao = dao;
    }


    public void saveBoredAction(BoredAction boredAction){
            dao.insert(boredAction);
    }

    public BoredAction boredAction(String key){
        return dao.get(key);
    }

    public List<BoredAction> getAllActions(){
        return dao.getAll();
    }

    public LiveData<List<BoredAction>> getAllLive(){
        return dao.getAllLive();
    }

    public void deleteBoredAction(BoredAction boredAction){
        dao.delete(boredAction);
    }

}
