package com.lobxy.moviesapp.Retrofit;

import com.lobxy.moviesapp.Model.MoviesData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitClientInstance {

    @GET("movie/top_rated")
    Call<MoviesData> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MoviesData> getUpcomingMovies(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<MoviesData> getPopularMovies(@Query("api_key") String api_key);


}
