package com.example.todo.presentation.main;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.data.BoredApiClient;
import com.example.todo.model.BoredAction;
import com.example.todo.presentation.Intro.IntroActivity;

import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends AppCompatActivity {

    private TextView category_text, category_spinner_text, middle_text, price_text,
            price_small, price_range_bar_text, access_text, access_range_bar_text, participant_text;
    private ImageView participant_image, heart_image;
    private SimpleRangeView access_range_bar, price_range_bar;
    private ProgressBar progressBar;
    private Spinner spinner;
    private Button next_bt;


    public static void start(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Id();
        App.prefs.isShown();

    }

    private void Id() {
        next_bt = findViewById(R.id.next_btn);
        spinner = findViewById(R.id.spinner);
        access_range_bar = findViewById(R.id.range_bar_acces);
        progressBar = findViewById(R.id.progress_bar);
        access_text = findViewById(R.id.access_text_01);
        access_range_bar_text = findViewById(R.id.range_bar_acces_text);
        price_range_bar = findViewById(R.id.range_bar_price);
        price_range_bar_text = findViewById(R.id.range_bar_price_text);
        price_text = findViewById(R.id.price_text);
        price_small = findViewById(R.id.price_text_small);
        participant_text = findViewById(R.id.participant_text);
        participant_image = findViewById(R.id.participant_image);
        category_text = findViewById(R.id.category_text);
        category_spinner_text = findViewById(R.id.category_small);
        middle_text = findViewById(R.id.middle_text);
        heart_image = findViewById(R.id.img_heart);
    }

    public void next_click(View view) {
        App.boredApiClient.getAction(null, null, null,
                null,null, new BoredApiClient.BoredActionCallback() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(BoredAction boredAction) {
                price_text.setText(boredAction.getPrice().toString()+"$");
                middle_text.setText(boredAction.getActivity());
                category_text.setText(boredAction.getType());
                progressBar.setProgress((int)(boredAction.getAccessibility()*100), true);
                switch (boredAction.getParticipants()){
                    case 1:
                        participant_image.setImageResource(R.drawable.ic_user);
                        break;
                    case 2:
                        participant_image.setImageResource(R.drawable.ic_user_two);
                        break;
                    case 3:
                        participant_image.setImageResource(R.drawable.ic_user_three);
                        break;
                    case 4:
                        participant_image.setImageResource(R.drawable.ic_user_four);
                        break;
                }
            }

            @Override
            public void onFailure(Exception exception) {
                Log.d("ololo", exception.getMessage());
            }
        });

    }
}

