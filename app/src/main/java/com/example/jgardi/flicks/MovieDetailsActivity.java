package com.example.jgardi.flicks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jgardi.flicks.model.Config;
import com.example.jgardi.flicks.model.Movie;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private Movie movie;

    @BindView(R.id.ivPreviewImage) ImageView previewImage;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;
    @BindView(R.id.rbVoteAverage) RatingBar rbVoteAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ButterKnife.bind(this);

        movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.i(TAG, String.format("Showing details for '%s'", movie.getTitle()));

        Config config = Parcels.unwrap(getIntent().getParcelableExtra(Config.class.getSimpleName()));
        Glide.with(getApplicationContext())
                .load(config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath()))
                .bitmapTransform(new RoundedCornersTransformation(getApplicationContext(), 15, 0))
                .placeholder(R.drawable.flicks_backdrop_placeholder)
                .error(R.drawable.flicks_backdrop_placeholder)
                .into(previewImage);

        previewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MovieTrailerActivity.class);
                intent.putExtra("id", movie.getId());
                getApplicationContext().startActivity(intent);
            }
        });

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
    }
}
