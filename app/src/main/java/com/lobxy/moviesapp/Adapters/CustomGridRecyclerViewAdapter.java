package com.lobxy.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.Model.MoviesCollectionDetail.MoviesCollectionDetails;
import com.lobxy.moviesapp.R;

import java.util.List;

public class CustomGridRecyclerViewAdapter extends RecyclerView.Adapter<CustomGridRecyclerViewAdapter.CustomViewHolder> {
    private List<MoviesCollectionDetails> moviesCollectionDetailsList;

    private Context context;

    private OnGridRVItemClickListener recyclerviewItemClickListener;

    public CustomGridRecyclerViewAdapter(Context context, List<MoviesCollectionDetails> moviesCollectionDetailsList, OnGridRVItemClickListener listener) {
        this.context = context;
        this.moviesCollectionDetailsList = moviesCollectionDetailsList;
        this.recyclerviewItemClickListener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_grid_item, parent, false);
        return new CustomViewHolder(view, recyclerviewItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.text_title.setText(moviesCollectionDetailsList.get(position).getTitle());

        //add poster image url into imageUrl.
        String image_url = "https://image.tmdb.org/t/p/original/" + moviesCollectionDetailsList.get(position).getPosterPath();

        //show it to user.
        Glide.with(context).load(image_url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesCollectionDetailsList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView text_title;

        OnGridRVItemClickListener listener;

        public CustomViewHolder(@NonNull View itemView, OnGridRVItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            imageView = itemView.findViewById(R.id.grid_imageview);
            text_title = itemView.findViewById(R.id.grid_textview_title);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnGridRVItemClickListener {
        void onItemClick(int position);
    }

}
