package com.lobxy.moviesapp.View.Detail.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.View.Detail.Model.Cast;
import com.lobxy.moviesapp.utils.CommonUtils;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_cast, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.text_character_name.setText(castList.get(position).getCharacter());
        holder.text_person_name.setText(castList.get(position).getName());

        String profilePath = castList.get(position).getProfilePath();

        if (profilePath != null && !profilePath.isEmpty()) {
            //Set image here.
            String url = CommonUtils.IMAGE_URL + profilePath;
            Glide.with(context).load(url).into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_person_placeholder);
        }

    }

    @Override
    public int getItemCount() {
        if (castList.size() > 10)
            return 10;
        else return castList.size();
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
