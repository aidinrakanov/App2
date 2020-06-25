package com.example.todo.presentation.Intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.todo.SharedPrefs;
import com.example.todo.presentation.main.MainActivity;
import com.example.todo.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

public class IntroActivity extends AppCompatActivity {

    ViewPager viewPager;
    Button next, skip;
    DotsIndicator dotsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new IntroPagerAdapter(getSupportFragmentManager()));
        dotsIndicator = findViewById(R.id.dots_indicator);
        dotsIndicator.setViewPager(viewPager);
        next = findViewById(R.id.next);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.start(IntroActivity.this);
                saveIsShown();
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nexxt();

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (position == IntroPagerAdapter.PAGES_COUNT - 1) {
                    next.setText("Start");
                    skip.setVisibility(View.GONE);

                } else {
                    next.setText("Next");
                    skip.setVisibility(View.VISIBLE);
                } }});
    }

    private void saveIsShown() {
        SharedPrefs sp = new SharedPrefs(this);
            sp.saveIsShown();

    }


    private void nexxt() {
        if (viewPager.getCurrentItem() < IntroPagerAdapter.PAGES_COUNT - 1) {
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
        } else {
            MainActivity.start(IntroActivity.this);
            saveIsShown();
            finish();
        }
    }

    public class IntroPagerAdapter extends FragmentPagerAdapter {
        public static final int PAGES_COUNT = 3;

        public IntroPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return IntroFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return PAGES_COUNT;
        }
    }

}