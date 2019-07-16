package com.lobxy.moviesapp.View;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.lobxy.moviesapp.Model.MoviesDataResults;
import com.lobxy.moviesapp.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            MoviesDataResults moviesData = (MoviesDataResults) bundle.getSerializable("MovieDetails");
            Log.i("Details", "onCreate: Title:  " + moviesData.getTitle());
        }
    }
}
