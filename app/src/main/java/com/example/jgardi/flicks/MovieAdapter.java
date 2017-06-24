package com.example.jgardi.flicks;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jgardi.flicks.model.Config;
import com.example.jgardi.flicks.model.Movie;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @author Joseph Gardi
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private String TAG = getClass().getName();
    private MovieDbInteractor movieInteractor;
    private Context context;


    public MovieAdapter(final MovieDbInteractor movieInteractor) {
        this.movieInteractor = movieInteractor;

        movieInteractor.loadMovies().subscribe(new Consumer<Movie>() {
            @Override
            public void accept(Movie movie) throws Exception {
                Log.i(TAG, "accept: " + movieInteractor.getMovies().size());
                notifyItemChanged(movieInteractor.getMovies().size() - 1);
            }
        });
    }


    //region RecyclerView.Adapter impementation

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "create holder");
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        ViewHolder holder = new ViewHolder(movieView);
        ButterKnife.bind(holder, movieView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.i(TAG, "BIND");
        final Movie movie = movieInteractor.getMovies().get(position);


        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());


        movieInteractor.getConfig(new Consumer<Config>() {
            @Override
            public void accept(Config config) throws Exception {
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                loadImage(config.getImageUrl(config.getPosterSize(), movie.getPosterPath()),
                        R.drawable.flicks_movie_placeholder,
                        holder.ivPosterImage);
            } else {
                loadImage(config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath()),
                        R.drawable.flicks_backdrop_placeholder,
                        holder.ivBackdropImage);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position != RecyclerView.NO_POSITION) {
                        // get the movie at the position, this won't work if the class is static
                        movieInteractor.getConfig(new Consumer<Config>() {
                            @Override
                            public void accept(Config config) throws Exception {
                                Movie movie = movieInteractor.getMovies().get(position);
                                // create intent for the new activity
                                Intent intent = new Intent(context, MovieDetailsActivity.class);
                                // serialize the movie using parceler, use its short name as a key
                                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                                intent.putExtra(Config.class.getSimpleName(), Parcels.wrap(config));
                                // show the activity
                                context.startActivity(intent);
                            }
                        });
                    }
                }
            });
            }
        });

    }


    @Override
    public int getItemCount() {
        return movieInteractor.getMovies().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.ivPosterImage) ImageView ivPosterImage;
        @Nullable
        @BindView(R.id.ivBackdropImage) ImageView ivBackdropImage;
        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.tvOverview) TextView tvOverview;

        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

    }
    //endregion


    private void loadImage(String imageUrl, int placeHolderId, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .bitmapTransform(new RoundedCornersTransformation(context, 15, 0))
                .placeholder(R.drawable.flicks_movie_placeholder)
                .error(R.drawable.flicks_movie_placeholder)
                .into(imageView);
    }
}
