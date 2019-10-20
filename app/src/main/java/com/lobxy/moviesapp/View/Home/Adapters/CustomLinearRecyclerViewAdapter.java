package com.lobxy.moviesapp.View.Home.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.View.Home.Model.MoviesCollectionDetails;
import com.lobxy.moviesapp.utils.CommonUtils;

import java.util.List;

public class CustomLinearRecyclerViewAdapter extends RecyclerView.Adapter<CustomLinearRecyclerViewAdapter.ViewHolder> {

    private List<MoviesCollectionDetails> moviesCollectionDetailsList;

    private Context context;

    private OnRecyclerViewItemClickListener recyclerviewItemClickListener;

    public CustomLinearRecyclerViewAdapter(Context context, List<MoviesCollectionDetails> moviesCollectionDetailsList, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.moviesCollectionDetailsList = moviesCollectionDetailsList;
        this.recyclerviewItemClickListener = listener;
    }

    @NonNull
    @Override
    public CustomLinearRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(view, recyclerviewItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomLinearRecyclerViewAdapter.ViewHolder holder, int i) {
        holder.text_title.setText(moviesCollectionDetailsList.get(i).getTitle());
        holder.text_overview.setText(moviesCollectionDetailsList.get(i).getOverview());
        holder.text_rating.setText(String.valueOf(moviesCollectionDetailsList.get(i).getVoteAverage()));

        //add poster image url into imageUrl.
        String image_url = CommonUtils.IMAGE_URL + moviesCollectionDetailsList.get(i).getPosterPath();

        //show it to user.
        Glide.with(context).load(image_url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return moviesCollectionDetailsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageView;
        TextView text_title;
        TextView text_overview;
        TextView text_rating;

        OnRecyclerViewItemClickListener listener;

        public ViewHolder(@NonNull View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);

            this.listener = listener;

            imageView = itemView.findViewById(R.id.list_image);
            text_title = itemView.findViewById(R.id.list_text_title);
            text_overview = itemView.findViewById(R.id.list_text_overview);
            text_rating = itemView.findViewById(R.id.list_text_rating);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

}
