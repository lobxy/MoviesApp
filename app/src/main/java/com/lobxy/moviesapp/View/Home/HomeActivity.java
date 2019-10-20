package com.lobxy.moviesapp.View.Home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.View.Home.Adapters.SectionsPagerAdapter;
import com.lobxy.moviesapp.View.Home.Fragments.fragment_popular;
import com.lobxy.moviesapp.View.Home.Fragments.fragment_top_rated;
import com.lobxy.moviesapp.View.Home.Fragments.fragment_upcoming;
import com.lobxy.moviesapp.utils.CommonUtils;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    //todo: Load data accordingly for movies and tv Series on button clicks.

    private ImageButton btn_series;
    private ImageButton btn_movies;

    private SearchView searchView;

    private boolean showing_movies = true; //will be used to check whether to load movies or tv series.

    private LinearLayout categoriesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();

        setToolBar();

        btn_series.setOnClickListener(this);
        btn_movies.setOnClickListener(this);

    }

    private void setToolBar() {

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
    }

    private void initViews() {
        categoriesContainer = findViewById(R.id.toolbar_home_categories);

        btn_movies = findViewById(R.id.home_button_movies);
        btn_series = findViewById(R.id.home_button_series);
        searchView = findViewById(R.id.home_search_bar);

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoriesContainer.setVisibility(View.GONE);
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                CommonUtils.hideKeyboard(HomeActivity.this);
                categoriesContainer.setVisibility(View.VISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    // searchView expanded
                    categoriesContainer.setVisibility(View.GONE);
                } else {
                    // searchView not expanded
                    categoriesContainer.setVisibility(View.VISIBLE);
                    CommonUtils.hideKeyboard(HomeActivity.this);
                    searchView.setIconified(true);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_button_movies:
                if (!showing_movies) {
                    //load movies.
                }
                break;
            case R.id.home_button_series:
                if (showing_movies) {
                    //load series.
                }
                break;
        }
    }

}