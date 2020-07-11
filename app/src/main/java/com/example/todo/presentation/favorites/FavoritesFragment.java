package com.example.todo.presentation.favorites;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private FavoritesAdapter favoritesAdapter;
    private int pos;
    private FavoritesAdapter adapter;
    private ArrayList<BoredAction> list = new ArrayList<>();
    LikeButton likeButton;

    public FavoritesFragment() {
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
        list.addAll(App.boredStorage.getAllActions());
        adapter = new FavoritesAdapter(list);
        recyclerView.setAdapter(adapter);

        adapter.FavoritesAdapter(new Click_Interface() {
            @Override
            public void onItemClick(int pos) {
                App.boredStorage.deleteBoredAction(list.get(pos));
            }
        });


    }
}
