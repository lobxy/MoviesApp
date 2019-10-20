package com.lobxy.moviesapp.View.Home.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.View.Home.Model.MoviesCollectionDetails;
import com.lobxy.moviesapp.utils.CommonUtils;

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
                .inflate(R.layout.grid_item_recyclerview_home, parent, false);
        return new CustomViewHolder(view, recyclerviewItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        //add poster image url into imageUrl.
        String image_url = CommonUtils.IMAGE_URL + moviesCollectionDetailsList.get(position).getPosterPath();

        //show it to user.
        Glide.with(context).load(image_url).into(holder.imageView);
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(context, moviesCollectionDetailsList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return moviesCollectionDetailsList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ImageView imageView;

        OnGridRVItemClickListener listener;

        public CustomViewHolder(@NonNull View itemView, OnGridRVItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            imageView = itemView.findViewById(R.id.grid_imageview);

            imageView.setOnClickListener(this);
            imageView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onItemLongClick(getAdapterPosition());
            return false;
        }
    }

    public interface OnGridRVItemClickListener {
        void onItemClick(int position);

        void onItemLongClick(int position);
    }

}
