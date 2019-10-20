package com.lobxy.moviesapp.View.Home.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.Retrofit.RetrofitClientInstance;
import com.lobxy.moviesapp.Retrofit.RetrofitServices;
import com.lobxy.moviesapp.View.Detail.DetailActivity;
import com.lobxy.moviesapp.View.Home.Adapters.CustomGridRecyclerViewAdapter;
import com.lobxy.moviesapp.View.Home.Model.MoviesCollectionData;
import com.lobxy.moviesapp.View.Home.Model.MoviesCollectionDetails;
import com.lobxy.moviesapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class fragment_popular extends Fragment implements CustomGridRecyclerViewAdapter.OnGridRVItemClickListener {

    public fragment_popular() {
    }


    private RecyclerView recyclerView;

    private ProgressBar progressBar;

    private List<MoviesCollectionDetails> list = new ArrayList<>();

    private boolean isScrolling = false;

    private int pageCount = 1; //used for loading initial and extra data as user scrolls more and more.
    private int currentItemsOnScreen = 0;
    private int totalItems = 0;
    private int scrolledOutItems = 0;

    private CustomGridRecyclerViewAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_popular, container, false);

        recyclerView = rootView.findViewById(R.id.fragment_popular_recyclerview);
        progressBar = rootView.findViewById(R.id.fragment_popular_progressbar);

        if (!connectivity()) {
            Toast.makeText(getContext(), "Not connected to internet", Toast.LENGTH_SHORT).show();
        } else {
            getData();
        }

        return rootView;
    }

    private void getData() {
        isScrolling = false;//setting it false so that user can't scroll down till response for new data is received and set.

        Log.i(TAG, "problem: Current list size: " + list.size());
        Log.i(TAG, "problem: Page Count: " + pageCount);

        RetrofitClientInstance instance = RetrofitServices.getRetrofitInstance().create(RetrofitClientInstance.class);
        Call<MoviesCollectionData> call = instance.getPopularMovies(CommonUtils.APP_KEY, pageCount);
        call.enqueue(new Callback<MoviesCollectionData>() {
            @Override
            public void onResponse(Call<MoviesCollectionData> call, Response<MoviesCollectionData> response) {

                if (response.code() == 200) {
                    //get list of TopRatedResult for recyclerView and initialize adapter.
                    list.addAll(response.body().getMoviesDataResults());
                    Log.i(TAG, "problem: Updated list size: " + list.size());

                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    } else {
                        setRecyclerView();
                    }

                } else if (response.code() == 401) {
                    Toast.makeText(getActivity(), "Invalid Key", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 404) {
                    Toast.makeText(getActivity(), "Content not available", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Internal error", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: API Error code not found");
                }

            }

            @Override
            public void onFailure(Call<MoviesCollectionData> call, Throwable t) {
                Toast.makeText(getActivity(), "Request Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "retrofit call failure cause: " + t.getMessage());
            }
        });
    }

    private void setRecyclerView() {
        adapter = new CustomGridRecyclerViewAdapter(getActivity(), list, this);

        final GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItemsOnScreen = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrolledOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItemsOnScreen + scrolledOutItems) >= totalItems) {
                    Log.i(TAG, "problem: reached end: " + totalItems);
                    pageCount++;
                    getData();

                }

            }
        });
    }

    private boolean connectivity() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onItemClick(int position) {
        MoviesCollectionDetails object = list.get(position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("MovieId", object.getId());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(getActivity(), list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
    }
}
