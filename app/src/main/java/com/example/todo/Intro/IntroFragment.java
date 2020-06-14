package com.example.todo.Intro;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.example.todo.R;


public class IntroFragment extends Fragment {
    Button next, skip, start;
    ImageView image;

    public IntroFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        next = view.findViewById(R.id.next);
        start = view.findViewById(R.id.startApp);
        skip = view.findViewById(R.id.skip);
        image = view.findViewById(R.id.intro_image);


        int pos = getArguments().getInt("pos");
        switch (pos){
            case 0:
                view.setBackgroundColor(Color.LTGRAY);
                image.setImageResource(R.drawable.jd);
                break;
            case 1:
                view.setBackgroundColor(Color.YELLOW);
                image.setImageResource(R.drawable.en);
                break;
            case 2:
                view.setBackgroundColor(Color.DKGRAY);
                image.setImageResource(R.drawable.st);
                break;
    }
}
}
