package com.example.todo.presentation.Intro;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.todo.R;


public class IntroFragment extends Fragment {
    private static final String ARG_POSITION = "position";

    public static Fragment newInstance(int position) {
        Fragment fragment = new IntroFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_POSITION, position);
        fragment.setArguments(bundle);
        return fragment;
    }
    LottieAnimationView lottie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       lottie = view.findViewById(R.id.intro_lottie);



        int pos = getArguments().getInt("pos");
        switch (pos){
            case 0:
                view.setBackgroundColor(Color.DKGRAY);
                lottie.setAnimation(R.raw.loading);
                break;
            case 1:
                view.setBackgroundColor(Color.YELLOW);
                lottie.setAnimation(R.raw.mobile);
                break;
            case 2:
                view.setBackgroundColor(Color.LTGRAY);
                lottie.setAnimation(R.raw.dancin);
                break;
        }
    }
}