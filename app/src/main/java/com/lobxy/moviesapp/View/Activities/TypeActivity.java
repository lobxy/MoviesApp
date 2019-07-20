package com.lobxy.moviesapp.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.lobxy.moviesapp.PrefManager;
import com.lobxy.moviesapp.R;

public class TypeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image_movies;
    private ImageView image_person;
    private ImageView image_tv_series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        image_movies = findViewById(R.id.type_image_movies);
        image_tv_series = findViewById(R.id.type_image_tv_series);

        image_tv_series.setOnClickListener(this);
        image_movies.setOnClickListener(this);
        image_person.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.type_image_movies:
                saveUserPreference("Movies");
                break;
            case R.id.type_image_tv_series:
                saveUserPreference("Tv Series");
                break;
        }

    }

    private void saveUserPreference(String movies) {
        PrefManager manager = new PrefManager(this);
        manager.saveUserPreference(movies);

        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
