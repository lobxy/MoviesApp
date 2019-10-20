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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lobxy.moviesapp.R;
import com.lobxy.moviesapp.Retrofit.RetrofitClientInstance;
import com.lobxy.moviesapp.Retrofit.RetrofitServices;
import com.lobxy.moviesapp.View.Detail.DetailActivity;
import com.lobxy.moviesapp.View.Home.Adapters.CustomLinearRecyclerViewAdapter;
import com.lobxy.moviesapp.View.Home.Model.MoviesCollectionData;
import com.lobxy.moviesapp.View.Home.Model.MoviesCollectionDetails;
import com.lobxy.moviesapp.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class fragment_top_rated extends Fragment implements CustomLinearRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private RecyclerView recyclerView;

    private List<MoviesCollectionDetails> moviesCollectionDetailsList = new ArrayList<>();

    public fragment_top_rated() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);

        recyclerView = rootView.findViewById(R.id.fragment_top_rated_recyclerview);

        if (!connectivity()) {
            Toast.makeText(getContext(), "Not connected to internet", Toast.LENGTH_SHORT).show();
        } else {
            getData();
        }

        return rootView;
    }

    private void getData() {
        RetrofitClientInstance instance = RetrofitServices.getRetrofitInstance().create(RetrofitClientInstance.class);
        Call<MoviesCollectionData> call = instance.getTopRatedMovies(CommonUtils.APP_KEY);
        call.enqueue(new Callback<MoviesCollectionData>() {
            @Override
            public void onResponse(Call<MoviesCollectionData> call, Response<MoviesCollectionData> response) {

                if (response.code() == 200) {
                    //get list of TopRatedResult for recyclerView and initialize adapter.
                    List<MoviesCollectionDetails> list = response.body().getMoviesDataResults();
                    moviesCollectionDetailsList.addAll(list);
                    setRecyclerView(list);

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

    private void setRecyclerView(List<MoviesCollectionDetails> list) {
        CustomLinearRecyclerViewAdapter adapter = new CustomLinearRecyclerViewAdapter(getActivity(), list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private boolean connectivity() {
        ConnectivityManager manager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void onItemClick(int position) {
        MoviesCollectionDetails object = moviesCollectionDetailsList.get(position);
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("MovieId", object.getId());
        startActivity(intent);
    }
}
