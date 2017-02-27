package com.deepanshu.moviestore.storeone.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.deepanshu.moviestore.storeone.R;
import com.deepanshu.moviestore.storeone.models.Movies;
import com.deepanshu.moviestore.storeone.views.activities.MovieDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReleasedMoviesAdapter extends RecyclerView.Adapter<ReleasedMoviesAdapter.ViewHolder> {
    private List<Movies> moviesList;
    private Context mContext;
    private static final String MOVIE_ID = "movie_id";
    private static final String MOVIE_NAME = "movie_name";
    private static final String IMAGE_URL = "movie_poster";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        @BindView(R.id.movieCard) CardView movieCard;
        @BindView(R.id.moviePoster) ImageView moviePoster;
        @BindView(R.id.movieTitle) TextView movieTitle;
        @BindView(R.id.movieType) TextView movieType;
        @BindView(R.id.releaseDate) TextView releaseDate;
        @BindView(R.id.movieRating) TextView movieRating;
        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);

            movieCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            int itemPosition = getLayoutPosition();
            String movieId = moviesList.get(itemPosition).get_id();
            String movieName = moviesList.get(itemPosition).getTitle();
            String moviePoster= moviesList.get(itemPosition).getImage_url();
            if (id == R.id.movieCard){
                Intent detailsIntent = new Intent(mContext, MovieDetailActivity.class);
                detailsIntent.putExtra(MOVIE_ID,movieId);
                detailsIntent.putExtra(MOVIE_NAME,movieName);
                detailsIntent.putExtra(IMAGE_URL,moviePoster);
                mContext.startActivity(detailsIntent);
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReleasedMoviesAdapter(Context mContext, List<Movies> moviesList) {
        this.moviesList = moviesList;
        this.mContext = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReleasedMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.released_movie, parent, false);
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String thumbnailUrl = moviesList.get(position).getImage_url();
        String title = moviesList.get(position).getTitle();
        String type = moviesList.get(position).getMovie_type();
        String release = moviesList.get(position).getRelease_date();
        String rating = moviesList.get(position).getRating();

        if (null != thumbnailUrl && !thumbnailUrl.equals("")){
            setMovieThumbnail(holder.moviePoster,thumbnailUrl);
        }

        if (null != title && !title.equals("")){
            holder.movieTitle.setText(title);
        }

        if (null != type && !type.equals("")){
            holder.movieType.setText(type);
        }

        if (null != release && !release.equals("")){
            holder.releaseDate.setText(release);
        }

        if (null != rating && !rating.equals("")){
            int outOff = mContext.getResources().getInteger(R.integer.movie_rate_outoff);
            holder.movieRating.setText(rating+"/"+outOff);
        }
    }

    private void setMovieThumbnail(ImageView moviePoster, String thumbnailUrl) {
        Glide.with(mContext)
                .load(thumbnailUrl)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(moviePoster);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
