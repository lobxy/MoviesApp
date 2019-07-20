package com.lobxy.moviesapp.View.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lobxy.moviesapp.Adapters.CastRecyclerViewListAdapter;
import com.lobxy.moviesapp.Adapters.SimilarMoviesRecyclerViewListAdapter;
import com.lobxy.moviesapp.Model.MovieSingleDetails.Cast;
import com.lobxy.moviesapp.Model.MovieSingleDetails.MovieSingleDetail;
import com.lobxy.moviesapp.Model.MoviesCollectionDetail.MoviesCollectionDetails;
import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.Retrofit.RetrofitClientInstance;
import com.lobxy.moviesapp.Retrofit.RetrofitServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "Detail";

    private RecyclerView moviesRecyclerView;
    private RecyclerView castRecyclerView;

    private ImageView image_poster;

    private TextView text_title;
    private TextView text_overView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        castRecyclerView = findViewById(R.id.detail_recycler_cast);
        moviesRecyclerView = findViewById(R.id.detail_recycler_similar);

        image_poster = findViewById(R.id.detail_image_poster);

        text_overView = findViewById(R.id.detail_text_overview);
        text_title = findViewById(R.id.detail_text_title);

        getPassedData();
    }

    private void getPassedData() {
        int movieId = getIntent().getIntExtra("MovieId", 0);
        if (movieId != 0) {
            //get movie details.
            RetrofitClientInstance clientInstance = RetrofitServices.getRetrofitInstance().create(RetrofitClientInstance.class);
            Call<MovieSingleDetail> call = clientInstance.getMovieDetails(movieId, getResources().getString(R.string.API_KEY), "images,credits,similar");
            call.enqueue(new Callback<MovieSingleDetail>() {
                @Override
                public void onResponse(Call<MovieSingleDetail> call, Response<MovieSingleDetail> response) {
                    if (response.code() == 200) {
                        handleData(response.body());
                    } else if (response.code() == 401) {
                        Toast.makeText(DetailActivity.this, "Invalid Key", Toast.LENGTH_SHORT).show();
                    } else if (response.code() == 404) {
                        Toast.makeText(DetailActivity.this, "Content not available", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(DetailActivity.this, "Internal error", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onResponse: API Error code not found");
                    }
                }

                @Override
                public void onFailure(Call<MovieSingleDetail> call, Throwable t) {
                    Toast.makeText(DetailActivity.this, "API response failed", Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "onFailure: API response failed: " + t.getMessage());
                }
            });

        } else {
            Log.i(TAG, "getPassedData: Intent passed empty or null");
        }

    }

    private void handleData(MovieSingleDetail response) {
        //Todo: figure out to either show the poster images or not
        //List<Poster> posterList = response.getImages().getPosters();

        String title = response.getTitle();
        String overview = response.getOverview();
        StringBuilder genre = new StringBuilder();

        //Go through the genre list and show them into the textView.
        for (int i = 0; i < response.getGenres().size(); i++) {
            genre.append(response.getGenres().get(i).getName()).append("\n");
        }

        //get list of cast for the recyclerview.
        List<Cast> castList = response.getCredits().getCast();

        //get list of similar movies for the recyclerview.
        List<MoviesCollectionDetails> similarMoviesList = response.getData().getMoviesDataResults();

        //set contents
        text_title.setText(title);
        text_overView.setText(overview);

        String posterUrl = "https://image.tmdb.org/t/p/original/" + response.getPosterPath();
        Glide.with(this).load(posterUrl).into(image_poster);

        setCastRecyclerView(castList);
        setSimilarMoviesRecyclerView(similarMoviesList);
    }

    private void setSimilarMoviesRecyclerView(List<MoviesCollectionDetails> similarMoviesList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        moviesRecyclerView.setLayoutManager(linearLayoutManager);
        SimilarMoviesRecyclerViewListAdapter adapter = new SimilarMoviesRecyclerViewListAdapter(this, similarMoviesList);
        moviesRecyclerView.setAdapter(adapter);
        if (adapter.getItemCount() == 0) {
            Toast.makeText(this, "Movies not available", Toast.LENGTH_SHORT).show();
        } else {
            Log.i(TAG, "setSimilarMoviesRecyclerView: adapter count " + adapter.getItemCount());
        }
    }

    private void setCastRecyclerView(List<Cast> castList) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        castRecyclerView.setLayoutManager(linearLayoutManager);
        CastRecyclerViewListAdapter adapter = new CastRecyclerViewListAdapter(this, castList);
        castRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
