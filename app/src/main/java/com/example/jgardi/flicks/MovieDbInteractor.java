package com.example.jgardi.flicks;

import android.content.Context;
import android.util.Log;

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
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

/**
 * @author Joseph Gardi
 */
public class MovieDbInteractor {
    private AsyncHttpClient client = new AsyncHttpClient();
    private BehaviorSubject<Config> configLoaded = BehaviorSubject.create();
    private PublishSubject<Throwable> movieError = PublishSubject.create();
    private List<Movie> movies = new ArrayList<>();
    private String TAG = getClass().getName();
    Context context;

    public MovieDbInteractor(Context context) {
        this.context = context;
    }


    public Observable<Movie> loadMovies() {
        return Observable.create(new ObservableOnSubscribe<Movie>() {
            @Override
            public void subscribe(final ObservableEmitter<Movie> movieLoaded) throws Exception {
                String url = context.getString(R.string.api_base_url) + "/configuration";
                RequestParams params = new RequestParams();
                params.put(context.getString(R.string.api_key_param), context.getString(R.string.api_key));
                Log.i(TAG, "the url is: " + url + "?" + params.toString());
                client.get(url, params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            Log.i(TAG, "got config but not yet published");
                            configLoaded.onNext(new Config(response));
                        } catch (JSONException e) {
                            configLoaded.onError(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        configLoaded.onError(throwable);
                    }
                });


                String moviesUrl = context.getString(R.string.api_base_url) + "/movie/now_playing";
                client.get(moviesUrl,
                        params, new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                Movie movie = new Movie(results.getJSONObject(i));
                                getMovies().add(movie);
                                movieLoaded.onNext(movie);
                            }
                        } catch (JSONException e) {
                            Log.i(TAG, "failed parsing movie");
                            movieError.onNext(e);
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.i(TAG, "failed to get ");
                        movieError.onNext(throwable);
                    }
                });
            }
        });
    }


    public void getConfig(Consumer<Config> consumer) {
        configLoaded.subscribe(consumer);
    }



    public List<Movie> getMovies() {
        return movies;
    }

    public BehaviorSubject<Config> getConfigLoaded() {
        return configLoaded;
    }

    public PublishSubject<Throwable> getMovieError() {
        return movieError;
    }
}
