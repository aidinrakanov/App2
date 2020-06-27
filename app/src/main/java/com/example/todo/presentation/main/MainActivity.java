package com.example.todo.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.data.BoredApiClient;
import com.example.todo.model.BoredAction;
import com.example.todo.presentation.Intro.IntroActivity;

public class MainActivity extends AppCompatActivity {


    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.prefs.isShown();

        App.boredApiClient.getAction(null, null, new BoredApiClient.BoredActionCallback() {
            @Override
            public void onSuccess(BoredAction boredAction) {
                Log.d("ololo", boredAction.toString());
            }

            @Override
            public void onFailure(Exception exception) {
                Log.d("ololo", exception.getMessage());
            }
        });
    }
}

