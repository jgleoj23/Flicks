package com.example.jgardi.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

public class MovieListActivity extends AppCompatActivity {

    private String TAG = getClass().getName();
    private AsyncHttpClient client = new AsyncHttpClient();

    @BindView(R.id.rvMovies) RecyclerView rvMovies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        ButterKnife.bind(this);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        final MovieDbInteractor movieInteractor = new MovieDbInteractor(getApplicationContext());
        final MovieAdapter adapter = new MovieAdapter(movieInteractor);
        rvMovies.setAdapter(adapter);



        movieInteractor.getConfigLoaded().doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof JSONException) {
                    logError("Failed parsing configuration", throwable, true);
                } else {
                    logError("Failed getting configuration", throwable, true);
                }
            }
        });

        movieInteractor.getMovieError().subscribe(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (throwable instanceof JSONException) {
                    logError("Failed to parse now playing movies", throwable, true);
                } else {
                    logError("Failed to get data from now_playing endpoint", throwable, true);
                }
            }
        });
    }



    private void logError(String message, Throwable error, boolean shouldAlertUser) {
        Log.e(TAG, message, error);

        if (shouldAlertUser) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)
                    .show();
        }
    }
}
