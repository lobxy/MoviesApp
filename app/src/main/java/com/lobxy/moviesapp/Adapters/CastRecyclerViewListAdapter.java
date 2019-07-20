package com.lobxy.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.Model.MovieSingleDetails.Cast;
import com.lobxy.moviesapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CastRecyclerViewListAdapter extends RecyclerView.Adapter<CastRecyclerViewListAdapter.CustomViewHolder> {

    private List<Cast> castList;
    private Context context;

    public CastRecyclerViewListAdapter(Context context, List<Cast> castList) {
        this.castList = castList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.text_character_name.setText(castList.get(position).getCharacter());
        holder.text_person_name.setText(castList.get(position).getName());

        //Set image here.
        String url = "https://image.tmdb.org/t/p/original/" + castList.get(position).getProfilePath();

        Glide.with(context).load(url).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        //Implement Circle ImageView.

        CircleImageView imageView;
        TextView text_person_name;
        TextView text_character_name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.cast_image_profile);
            text_person_name = itemView.findViewById(R.id.cast_text_name);
            text_character_name = itemView.findViewById(R.id.cast_text_character);

        }
    }
}
