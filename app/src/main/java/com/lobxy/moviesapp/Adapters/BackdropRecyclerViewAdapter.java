package com.lobxy.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.Model.MovieSingleDetails.Backdrop;
import com.lobxy.moviesapp.R;

import java.util.List;

public class BackdropRecyclerViewAdapter extends RecyclerView.Adapter<BackdropRecyclerViewAdapter.CustomViewHolder> {

    private List<Backdrop> posterList;
    private Context context;

    public BackdropRecyclerViewAdapter(Context context, List<Backdrop> posterList) {
        this.posterList = posterList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.backdrop_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String url = "https://image.tmdb.org/t/p/original/" + posterList.get(position).getFilePath();
        Glide.with(context).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (posterList.size() > 5) {
            return 5;
        } else {
            return posterList.size();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.backdrop_image);
        }
    }
}
