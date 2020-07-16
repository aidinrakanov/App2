package com.example.todo.presentation.main;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.data.BoredApiClient;
import com.example.todo.model.BoredAction;
import com.google.android.material.slider.RangeSlider;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;


public class MainFragment extends Fragment {
    private TextView category_text, middle_text, price_text;
    private ImageView participant_image;
    private RangeSlider access_range_bar, price_range_bar;
    private ProgressBar progressBar;
    private Spinner spinner;
    private Button next_bt, favorite_bt;
    List<Float> price_bar;
    private Float minPrice;
    private Float maxPrice;
    List<Float> access_bar;
    private Float minAccess;
    private Float maxAccess;
    String type;
    BoredAction boredAction_model;
    LikeButton likeButton;
    String key_like;




    public MainFragment() {
    }

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Id(view);
        spinnerSelect();
        PriceChange();
        AccessChange();
        next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_click();
            }
        });
        addToFavorite();
    }

    private void addToFavorite() {
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                App.boredStorage.saveBoredAction(boredAction_model);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                App.boredStorage.deleteBoredAction(boredAction_model);
            }
        });
    }

    private void Id(View view) {
        next_bt = view.findViewById(R.id.next_btn);
        spinner = view.findViewById(R.id.spinner);
        access_range_bar = view.findViewById(R.id.range_bar_acces);
        progressBar = view.findViewById(R.id.progress_bar);
        price_range_bar = view.findViewById(R.id.range_bar_price);
        price_text = view.findViewById(R.id.price_text);
        participant_image = view.findViewById(R.id.participant_image);
        category_text = view.findViewById(R.id.category_text);
        middle_text = view.findViewById(R.id.middle_text);
        likeButton = view.findViewById(R.id.img_heart);
    }

    public void next_click() {
        likeButton.setLiked(false);
        if (type!= null){
            if (type.equals("all")){
                type=null;}
        }
        App.boredApiClient.getAction(type, minPrice, maxPrice,
                minAccess, maxAccess, new BoredApiClient.BoredActionCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        try {
                            boredAction_model = boredAction;
                            key_like = boredAction.getKey();
                            price_text.setText(boredAction.getPrice().toString() + "$");
                            middle_text.setText(boredAction.getActivity());
                            category_text.setText(boredAction.getType());
                            progressBar.setProgress((int) (boredAction.getAccessibility() * 100), true);
                            switch (boredAction.getParticipants()) {
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
                        } catch (Exception e) {
                            price_text.setText("");
                            middle_text.setText("Press Next button");
                            category_text.setText("");
                            progressBar.setProgress(0);
                            Toast.makeText(getContext(), "Press 'Next'", Toast.LENGTH_SHORT).show();
                        }

                        BoredAction bored_model = App.boredStorage.boredAction(key_like);
                        if(bored_model != null){
                            likeButton.setLiked(true);
                        }

                    }

                    @Override
                    public void onFailure(Exception exception) {
                        Log.d("ololo", exception.getMessage());
                    }
                });
    }

    public void PriceChange() {
        price_range_bar.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                price_bar = slider.getValues();
                minPrice = price_bar.get(0);
                maxPrice = price_bar.get(1);
            }
        });
    }

    public void AccessChange() {
        access_range_bar.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                access_bar = slider.getValues();
                minAccess = access_bar.get(0);
                maxAccess = access_bar.get(1);
            }
        });
    }

    public void spinnerSelect() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
