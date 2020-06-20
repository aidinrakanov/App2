package com.example.todo.presentation.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.todo.R;
import com.example.todo.SharedPrefs;
import com.example.todo.presentation.Intro.IntroActivity;

public class MainActivity extends AppCompatActivity {


    public static void start (Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!isShown()) {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
            return;
        }

    }

    private boolean isShown() {
        SharedPrefs sharedPrefs = new SharedPrefs();
        return sharedPrefs.isShown();
    }


}
