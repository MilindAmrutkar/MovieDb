package com.example.android.moviedbassignment.network;

import com.example.android.moviedbassignment.models.Movie;
import com.example.android.moviedbassignment.models.MovieResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Milind Amrutkar on 11-02-2020.
 */
public interface Endpoints {

    @GET("movie/now_playing")
    Call<MovieResponse> getAllResults(@Query("api_key") String apiKey);


}
