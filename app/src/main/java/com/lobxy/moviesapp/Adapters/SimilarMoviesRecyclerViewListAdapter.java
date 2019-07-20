package com.lobxy.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.Model.MoviesCollectionDetail.MoviesCollectionDetails;
import com.lobxy.moviesapp.R;

import java.util.List;

public class SimilarMoviesRecyclerViewListAdapter extends RecyclerView.Adapter<SimilarMoviesRecyclerViewListAdapter.CustomViewHolder> {

    private List<MoviesCollectionDetails> moviesList;
    private Context context;

    public SimilarMoviesRecyclerViewListAdapter(Context context, List<MoviesCollectionDetails> moviesList) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.similar_movies_list_item, parent, false);
        return new SimilarMoviesRecyclerViewListAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        //add poster image url into imageUrl.
        String image_url = "https://image.tmdb.org/t/p/original/" + moviesList.get(position).getPosterPath();

        //show it to user.
        Glide.with(context).load(image_url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.similar_movies_image_poster);
        }
    }
}
