package com.example.jgardi.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jgardi.flicks.model.Config;
import com.example.jgardi.flicks.model.Movie;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author Joseph Gardi
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    private Config config;
    private Context context;


    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View moviewView = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(moviewView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());


        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            loadImage(config.getImageUrl(config.getPosterSize(), movie.getPosterPath()),
                    R.drawable.flicks_movie_placeholder,
                    holder.ivPosterImage);
        } else {
            loadImage(config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath()),
                    R.drawable.flicks_backdrop_placeholder,
                    holder.ivBackdropImage);
        }
    }

    private void loadImage(String imageUrl, int placeHolderId, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 15, 0))
                .placeholder(R.drawable.flicks_movie_placeholder)
                .error(R.drawable.flicks_movie_placeholder)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPosterImage;
        ImageView ivBackdropImage;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(View itemView) {
            super(itemView);

            ivPosterImage = (ImageView) itemView.findViewById(R.id.ivPosterImage);
            ivBackdropImage = (ImageView) itemView.findViewById(R.id.ivBackdropImage);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }



    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }
}
