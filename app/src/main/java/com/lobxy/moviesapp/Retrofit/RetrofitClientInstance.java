package com.lobxy.moviesapp.Retrofit;

import com.lobxy.moviesapp.Model.MovieSingleDetails.MovieSingleDetail;
import com.lobxy.moviesapp.Model.MoviesCollectionDetail.MoviesCollectionData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitClientInstance {

    @GET("movie/top_rated")
    Call<MoviesCollectionData> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/upcoming")
    Call<MoviesCollectionData> getUpcomingMovies(@Query("api_key") String api_key);

    @GET("movie/popular")
    Call<MoviesCollectionData> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/{id}")
    Call<MovieSingleDetail> getMovieDetails(@Path("id") String id, @Query("api_key") String api_key, @Query("append_to_response") String filters);

}
