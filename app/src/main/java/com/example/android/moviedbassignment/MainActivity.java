package com.example.android.moviedbassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.android.moviedbassignment.adapter.MoviesAdapter;
import com.example.android.moviedbassignment.models.Movie;
import com.example.android.moviedbassignment.models.MovieResponse;
import com.example.android.moviedbassignment.network.ApiClient;
import com.example.android.moviedbassignment.network.Endpoints;
import com.example.android.moviedbassignment.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvMovies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Endpoints apiService =
                ApiClient.getClient().create(Endpoints.class);

        Call<MovieResponse> call = apiService.getAllResults(Constants.API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
               /* Log.d(TAG, "onResponse: Title: " + movies.get(0).getTitle() +
                        " Poster Path: " + movies.get(0).getPosterPath() +
                        " Release date: " + movies.get(0).getReleaseDate());*/

                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.movie_single_row, getApplicationContext()));
                Log.d(TAG, "onResponse: Movies recevied: " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });




    }
}
