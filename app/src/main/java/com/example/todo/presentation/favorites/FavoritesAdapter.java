package com.example.todo.presentation.favorites;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.BoredAction;
import com.google.android.material.slider.RangeSlider;
import com.like.LikeButton;

import java.util.ArrayList;
import java.util.List;


public class FavoritesAdapter extends RecyclerView.Adapter <FavoritesAdapter.ViewHolder> {

    List<BoredAction> models= new ArrayList<>();



    public Click_Interface onItemClick;

    public FavoritesAdapter(List<BoredAction> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate
                (R.layout.favor_list, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(models.get(position));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public  void FavoritesAdapter(Click_Interface onItemClick) {
        this.onItemClick = onItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView category_text, middle_text, price_text;
        ImageView participant_image;
        ProgressBar progressBar;
        LikeButton likeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_bar_Fav);
            price_text = itemView.findViewById(R.id.price_text_Fav);
            participant_image = itemView.findViewById(R.id.participant_image_Fav);
            category_text = itemView.findViewById(R.id.category_text_Fav);
            middle_text = itemView.findViewById(R.id.middle_text_Fav);
            likeButton = itemView.findViewById(R.id.img_heart_Fav);
            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClick.onItemClick(getAdapterPosition());
                }
            });

        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        public void bind(BoredAction boredAction) {
            middle_text.setText(boredAction.getActivity());
            price_text.setText(boredAction.getPrice().toString());
            participant_image.setImageResource(boredAction.getParticipants());
            progressBar.setProgress((int) (boredAction.getAccessibility() * 100), true);
            category_text.setText(boredAction.getType());

        }
    }
}
