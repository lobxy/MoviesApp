package com.lobxy.moviesapp.Retrofit;

import com.lobxy.moviesapp.Model.TopRated;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClientInstance {

    @GET("movie/top_rated")
    Call<TopRated> getTopRatedMovies(@Query("api_key") String api_key);

}
