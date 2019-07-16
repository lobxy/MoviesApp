package com.lobxy.moviesapp.View;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.lobxy.moviesapp.Model.MoviesData;
import com.lobxy.moviesapp.Model.MoviesDataResults;
import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.Retrofit.RetrofitClientInstance;
import com.lobxy.moviesapp.Retrofit.RetrofitServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private static final String API_KEY = "fb5216125b473827f4adeba21d6042c3";
    private RecyclerView recyclerView;

    //private List<TopRatedResult> topRatedResultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.main_recyclerView);

        if (!connectivity()) {
            Toast.makeText(this, "Not connected to internet", Toast.LENGTH_SHORT).show();
        } else {
            getData();
        }

    }

    private void getData() {

        RetrofitClientInstance instance = RetrofitServices.getRetrofitInstance().create(RetrofitClientInstance.class);
        Call<MoviesData> call = instance.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesData>() {
            @Override
            public void onResponse(Call<MoviesData> call, Response<MoviesData> response) {

                if (response.code() == 200) {
                    //get list of TopRatedResult for recyclerView and initialize adapter.
                    List<MoviesDataResults> list = response.body().getMoviesDataResults();
                    setRecyclerView(list);

                } else if (response.code() == 401) {
                    Toast.makeText(MainActivity.this, "Invalid Key", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 404) {
                    Toast.makeText(MainActivity.this, "Content not available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Internal error", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: API Error code not found");
                }

            }

            @Override
            public void onFailure(Call<MoviesData> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "retrofit call failure cause: " + t.getMessage());
            }
        });
    }

    private void setRecyclerView(List<MoviesDataResults> list) {
//        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(this, list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(adapter);
    }

    private boolean connectivity() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
