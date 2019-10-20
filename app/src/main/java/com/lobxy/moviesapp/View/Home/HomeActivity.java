package com.lobxy.moviesapp.View.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.View.Home.Adapters.SectionsPagerAdapter;
import com.lobxy.moviesapp.View.Home.Fragments.fragment_popular;
import com.lobxy.moviesapp.View.Home.Fragments.fragment_top_rated;
import com.lobxy.moviesapp.View.Home.Fragments.fragment_upcoming;

public class HomeActivity extends AppCompatActivity {

    //todo: Load data accordingly for movies and tv Series on button clicks.

    ImageButton btn_series;
    ImageButton btn_movies;

    SearchView searchView;

    Boolean showing_movies = true; //will be used to check whether to load movies or tv series.
    // Primarily we'll show movies so set to true.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewPager viewPager = findViewById(R.id.view_pager);
        TabLayout tabs = findViewById(R.id.tabs);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        sectionsPagerAdapter.setupFragments(new fragment_upcoming(), "");
        sectionsPagerAdapter.setupFragments(new fragment_popular(), "");
        sectionsPagerAdapter.setupFragments(new fragment_top_rated(), "");

        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        tabs.getTabAt(0).setIcon(R.drawable.ic_upcoming);
        tabs.getTabAt(1).setIcon(R.drawable.ic_popular);
        tabs.getTabAt(2).setIcon(R.drawable.ic_top_rated);

        btn_movies = findViewById(R.id.home_button_movies);
        btn_series = findViewById(R.id.home_button_series);
        searchView = findViewById(R.id.home_search_bar);

        btn_series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check whether tv series is not shown.
                if (showing_movies) {
                    //load data.
                }
            }
        });

        btn_movies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check whether movies is not shown.
                if (!showing_movies) {
                    //load data.
                }
            }
        });

    }

}