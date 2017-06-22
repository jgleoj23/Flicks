package com.example.jgardi.flicks.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Represents a movie from the movie db api.
 *
 * @author Joseph Gardi
 */

public class Movie {

    private String title;
    private  String overview;
    private String posterPath;
    private String backdropPath;


    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");

    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
