package com.example.android.moviedbassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {

    String posterPath, title, releaseDate;
    ImageView ivPosterPath;
    TextView tvMovieTitle, tvReleaseDate;

    public static final String TAG = MovieDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ivPosterPath = findViewById(R.id.ivImgPoster);
        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvReleaseDate = findViewById(R.id.tvMovieReleaseDate);

        releaseDate = getIntent().getExtras().getString("release_date");
        title = getIntent().getExtras().getString("movie_title");
        posterPath = getIntent().getExtras().getString("poster_path");

        Log.d(TAG, "onCreate: " + releaseDate + " " + title + " " + posterPath);

        tvMovieTitle.setText(title);
        tvReleaseDate.setText(releaseDate);
        Glide.with(this)
                .load(posterPath)
                .into(ivPosterPath);
    }
}
