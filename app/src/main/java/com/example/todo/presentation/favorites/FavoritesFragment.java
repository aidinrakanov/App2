package com.example.todo.presentation.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.todo.App;
import com.example.todo.R;
import com.example.todo.data.db.BoredDatabase;
import com.example.todo.data.local.BoredStorage;
import com.example.todo.model.BoredAction;
import com.google.android.material.slider.RangeSlider;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private FavoritesAdapter favoritesAdapter;
    private int pos;
    private FavoritesAdapter adapter;
    private ArrayList<BoredAction> list = new ArrayList<>();
    LikeButton likeB;
    BoredAction boredAction;

    public FavoritesFragment() {
    }

    public static Fragment newInstance() {
        return new FavoritesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.favor_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FavoritesAdapter(list);
        recyclerView.setAdapter(adapter);
        likeB = view.findViewById(R.id.img_heart_Fav);
        adapter.FavoritesAdapter(new Click_Interface() {
            @Override
            public void onItemClick(int pos) {
                App.boredStorage.deleteBoredAction(list.get(pos));
                list.remove(pos);
                adapter.notifyDataSetChanged();
            }
        });
//        loadData();
        toRemove();
        loadData2();
    }

    private void toRemove() {
        if (likeB != null) {
            likeB.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    App.boredStorage.deleteBoredAction(boredAction);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData2();
    }

    @Override
    public void onPause() {
        super.onPause();
        loadData2();
    }

    private void loadData2() {
        list.clear();
        list.addAll(App.boredStorage.getAllActions());
        adapter.notifyDataSetChanged();

    }
}

//    private void loadData() {
//        App.getInstance().getDatabase().boredDao().getAllLive().observe(this, new Observer<List<BoredAction>>() {
//            @Override
//            public void onChanged(List<BoredAction> boredActions) {
//                list.clear();
//                list.addAll(boredActions);
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
