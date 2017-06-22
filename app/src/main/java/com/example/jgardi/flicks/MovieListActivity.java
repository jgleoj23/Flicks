package com.example.jgardi.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.jgardi.flicks.model.Config;
import com.example.jgardi.flicks.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieListActivity extends AppCompatActivity {

    private String TAG = getClass().getName();
    private AsyncHttpClient client = new AsyncHttpClient();
    private List<Movie> movies = new ArrayList<>();
    private Config config;

    private RecyclerView rvMovies;
    private MovieAdapter movieAdapter = new MovieAdapter(movies);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(movieAdapter);

        configure();
    }


    private void configure() {
        String url = getString(R.string.api_base_url) + "/configuration";
        RequestParams params = new RequestParams();
        params.put(getString(R.string.api_key_param), getString(R.string.api_key));
        Log.i(TAG, "the url is: " + url + "?" + params.toString());
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    config = new Config(response);
                    movieAdapter.setConfig(config);
                    loadNowPlaying();
                } catch (JSONException e) {
                    logError("Failed parsing configuration", e, true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed getting configuration", throwable, true);
            }
        });
    }


    private void loadNowPlaying() {
        String url = getString(R.string.api_base_url) + "/movie/now_playing";
        RequestParams params = new RequestParams();
        params.put(getString(R.string.api_key_param), getString(R.string.api_key));

        client.get(url, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray results = response.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        movies.add(new Movie(results.getJSONObject(i)));
                        movieAdapter.notifyItemChanged(movies.size() - 1);
                    }
                } catch (JSONException e) {
                    logError("Failed to parse now playing movies", e, true);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed to get data from now_playing endpoint", throwable, true);
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
