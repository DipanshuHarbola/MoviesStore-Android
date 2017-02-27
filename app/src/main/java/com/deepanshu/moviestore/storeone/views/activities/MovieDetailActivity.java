package com.deepanshu.moviestore.storeone.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.deepanshu.moviestore.storeone.R;
import com.deepanshu.moviestore.storeone.application.MyApp;
import com.deepanshu.moviestore.storeone.models.Movies;
import com.deepanshu.moviestore.storeone.services.RestAPI;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class MovieDetailActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    @BindView(R.id.movieDescription) TextView movieDescription;
    @BindView(R.id.releaseDate) TextView releaseDate;
    @BindView(R.id.director) TextView director;
    @BindView(R.id.writer) TextView writer;
    @BindView(R.id.stars) TextView stars;
    @BindView(R.id.movieType) TextView movieType;
    @BindView(R.id.duration) TextView duration;
    @BindView(R.id.movieTitle) TextView movieTitle;
    @BindView(R.id.movieImage) ImageView poster;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private MovieDetailActivity mContext;

    private static final String MOVIE_ID = "movie_id";
    private static final String IMAGE_URL = "movie_poster";

    private String movieId, moviePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        mContext = MovieDetailActivity.this;
        ((MyApp) getApplication()).getNetComponent().inject(mContext);
        ButterKnife.bind(mContext);

        getBundalData();
        initViews();

    }

    private void getBundalData() {
        Bundle bundle = getIntent().getExtras();
        movieId = bundle.getString(MOVIE_ID);
        moviePoster = bundle.getString(IMAGE_URL);
    }

    private void initViews() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (null != moviePoster && !moviePoster.equals("")){
            setMoviePoster(poster, moviePoster);
        }

        callForMovieDetail();
    }

    private void callForMovieDetail() {
        //Create a retrofit call object
        Call<Movies> posts = retrofit.create(RestAPI.class).getMovieInfo(movieId);

        //Enque the call
        posts.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                if (response.isSuccessful()){
                    Movies movies = response.body();
                    String mTitle = movies.getTitle();
                    String mDescription = movies.getDescription();
                    String mRelease = movies.getRelease_date();
                    String mDirector = movies.getDirector();
                    String mWriter = movies.getWriter();
                    String mStar = movies.getStars();
                    String mType = movies.getMovie_type();
                    String mDuration = movies.getDuration();

                    if (null != mTitle && !mTitle.equals("")){
                        movieTitle.setText(mTitle);
                    }
                    if (null != mDescription && !mDescription.equals("")){
                        movieDescription.setText(mDescription);
                    }
                    if (null != mRelease && !mRelease.equals("")){
                        releaseDate.setText(mRelease);
                    }
                    if (null != mDirector && !mDirector.equals("")){
                        director.setText(mDirector);
                    }
                    if (null != mWriter && !mWriter.equals("")){
                        writer.setText(mWriter);
                    }
                    if (null != mStar && !mStar.equals("")){
                        stars.setText(mStar);
                    }
                    if (null != mType && !mType.equals("")){
                        movieType.setText(mType);
                    }
                    if (null != mDuration && !mDuration.equals("")){
                        duration.setText(mDuration);
                    }
                }
                else{
                    int status = response.code();
                    Log.e("Status", "Status Code- "+status+"  Url- "+call.request().url());
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                Log.e("Error-url",call.request().url()+"");
            }
        });
    }

    public void setMoviePoster(ImageView imageView, String imgUrl){
        Glide.with(mContext)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home){
            super.onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
