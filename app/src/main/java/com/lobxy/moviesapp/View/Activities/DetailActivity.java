package com.lobxy.moviesapp.View.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.lobxy.moviesapp.Model.MoviesCollectionDetail.MoviesCollectionDetails;
import com.lobxy.moviesapp.R;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "Detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getPassedData();
    }

    private void getPassedData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            MoviesCollectionDetails moviesData = (MoviesCollectionDetails) bundle.getSerializable("MovieDetails");

            //set data to the views.

        } else {
            Log.i(TAG, "onCreate: Bundle Null");
            finish();
        }
    }

}
