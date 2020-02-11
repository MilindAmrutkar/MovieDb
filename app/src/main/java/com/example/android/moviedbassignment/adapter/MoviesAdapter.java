package com.example.android.moviedbassignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.moviedbassignment.MovieDetailsActivity;
import com.example.android.moviedbassignment.R;
import com.example.android.moviedbassignment.models.Movie;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Milind Amrutkar on 11-02-2020.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>{
    private List<Movie> movies;
    private int rowLayout;
    private Context context;
    private String posterBaseURL = "https://image.tmdb.org/t/p/w185/";

    //    https://image.tmdb.org/t/p/w185/z7FCF54Jvzv9Anxyf82QeqFXXOO.jpg

    public static final String TAG = "MoviesAdapter";

    public MoviesAdapter(List<Movie> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        if (movies != null) {
            holder.releaseDate.setText(movies.get(position).getReleaseDate());
            holder.movieTitle.setText(movies.get(position).getTitle());
            Glide.with(context)
                    .load(posterBaseURL + movies.get(position).getPosterPath())
                    //.placeholder(R.drawable.ic_account_box_black_24dp)
                    .into(holder.moviePoster);

            holder.btnBookTicket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MovieDetailsActivity.class);
                    intent.putExtra("release_date", movies.get(position).getReleaseDate());
                    intent.putExtra("movie_title", movies.get(position).getTitle());
                    intent.putExtra("poster_path", posterBaseURL + movies.get(position).getPosterPath());
                    intent.putExtra("overview", movies.get(position).getOverview());
                    context.startActivity(intent);
                }
            });

        } else {
            Log.d(TAG, "onBindViewHolder: Movies is null");
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieTitle;
        TextView releaseDate;
        ImageView moviePoster;
        Button btnBookTicket;

        public MovieViewHolder(View v) {
            super(v);

            movieTitle =  v.findViewById(R.id.tvMovieTitle);
            releaseDate = v.findViewById(R.id.tvMovieReleaseDate);
            moviePoster = v.findViewById(R.id.imgViewPoster);
            btnBookTicket = v.findViewById(R.id.btnBookTicket);
        }
    }

}
