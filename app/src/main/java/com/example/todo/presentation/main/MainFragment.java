package com.example.todo.presentation.main;

import android.content.Context;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.data.BoredApiClient;
import com.example.todo.model.BoredAction;
import com.google.android.material.slider.RangeSlider;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;


public class MainFragment extends Fragment {
    private TextView category_text, middle_text, price_text,
            price_text_small, access_text, participant_text;
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
    LottieAnimationView lottie;


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
        final Animation myAnim = AnimationUtils.loadAnimation
                (getContext(), R.anim.milkshake);
        Id(view);
        spinnerSelect();
        PriceChange();
        AccessChange();
        next_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next_click();
                v.startAnimation(myAnim);
            }
        });
        addToFavorite();
    }

    private void addToFavorite() {
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                try {
                    App.boredStorage.saveBoredAction(boredAction_model);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Press 'NEXT'", Toast.LENGTH_SHORT).show();
                    likeButton.setLiked(false);
                }
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
        price_text_small = view.findViewById(R.id.price_text_small);
        access_text = view.findViewById(R.id.access_text_01);
        participant_text = view.findViewById(R.id.participant_text);
        participant_image = view.findViewById(R.id.participant_image);
        category_text = view.findViewById(R.id.category_text);
        middle_text = view.findViewById(R.id.middle_text);
        likeButton = view.findViewById(R.id.img_heart);
        lottie = view.findViewById(R.id.lottie_load);
    }

    public void next_click() {
        likeButton.setLiked(false);
        if (type!= null){
            if (type.equals("all")){
                type=null;}
        }

            progress_load();
        App.boredApiClient.getAction(type, minPrice, maxPrice,
                minAccess, maxAccess, new BoredApiClient.BoredActionCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        try {
                            progress_loaded();
                            boredAction_model = boredAction;
                            key_like = boredAction.getKey();
                            price_text.setText(boredAction.getPrice().toString() + "$");
                            middle_text.setText(boredAction.getActivity());
                            category_text.setText(boredAction.getType());
                            ChangeColor();
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
                            middle_text.setText("Change Parameters");
                            category_text.setText("");
                            progressBar.setProgress(0);
                            Toast.makeText(getContext(), "Change Parameters", Toast.LENGTH_SHORT).show();
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

    private void progress_loaded() {
        lottie.setVisibility(View.GONE);
        category_text.setVisibility(View.VISIBLE);
        middle_text.setVisibility(View.VISIBLE);
        price_text.setVisibility(View.VISIBLE);
        price_text_small.setVisibility(View.VISIBLE);
        participant_text.setVisibility(View.VISIBLE);
        access_text.setVisibility(View.VISIBLE);
        participant_image.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        likeButton.setVisibility(View.VISIBLE);
    }

    private void progress_load() {
        lottie.setVisibility(View.VISIBLE);
        category_text.setVisibility(View.GONE);
        middle_text.setVisibility(View.GONE);
        price_text.setVisibility(View.GONE);
        price_text_small.setVisibility(View.GONE);
        participant_text.setVisibility(View.GONE);
        access_text.setVisibility(View.GONE);
        participant_image.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        likeButton.setVisibility(View.GONE);
    }

    private void ChangeColor() {
        if (category_text.getText().equals("education")){ category_text.setBackgroundResource(R.color.education); }
        if (category_text.getText().equals("recreational")){ category_text.setBackgroundResource(R.color.recreational); }
        if (category_text.getText().equals("social")){ category_text.setBackgroundResource(R.color.social); }
        if (category_text.getText().equals("diy")){ category_text.setBackgroundResource(R.color.diy); }
        if (category_text.getText().equals("charity")){ category_text.setBackgroundResource(R.color.charity); }
        if (category_text.getText().equals("cooking")){ category_text.setBackgroundResource(R.color.cooking); }
        if (category_text.getText().equals("relaxation")){ category_text.setBackgroundResource(R.color.relaxation); }
        if (category_text.getText().equals("music")){ category_text.setBackgroundResource(R.color.music); }
        if (category_text.getText().equals("busywork")){ category_text.setBackgroundResource(R.color.busywork); }
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
