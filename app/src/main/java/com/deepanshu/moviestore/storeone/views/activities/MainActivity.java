package com.deepanshu.moviestore.storeone.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.deepanshu.moviestore.storeone.R;
import com.deepanshu.moviestore.storeone.application.MyApp;
import com.deepanshu.moviestore.storeone.models.Movies;
import com.deepanshu.moviestore.storeone.services.RestAPI;
import com.deepanshu.moviestore.storeone.views.adapter.ReleasedMoviesAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @BindView(R.id.releasedMoviesList) RecyclerView releasedMoviesList;
    @BindView(R.id.toolbar) Toolbar toolbar;
    private MainActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        ((MyApp) getApplication()).getNetComponent().inject(mContext);
        ButterKnife.bind(this);
        
        initView();
    }

    private void initView() {
        setSupportActionBar(toolbar);
        LinearLayoutManager llmgr = new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
        releasedMoviesList.setLayoutManager(llmgr);
        releasedMoviesList.setHasFixedSize(true);

        callForGetMovies();

    }

    private void callForGetMovies() {
        //Create a retrofit call object
        Call<List<Movies>> posts = retrofit.create(RestAPI.class).getMyMovies();
        Log.e("POSTS-URL",posts.request().url()+"");

        //Enque the call
        posts.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {
                if (response.isSuccessful()){
                    List<Movies> moviesList = response.body();
                    if (null != moviesList && moviesList.size() > 0) {
                        ReleasedMoviesAdapter releasedMoviesAdapter = new ReleasedMoviesAdapter(mContext, moviesList);
                        releasedMoviesList.setAdapter(releasedMoviesAdapter);
                    }
                }
                else{
                    int status = response.code();
                    Log.e("Status", "Status Code- "+status+"  Url- "+call.request().url());
                }
            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                Toast.makeText(mContext, mContext.getResources().getString(R.string.unexpected_error), Toast.LENGTH_SHORT).show();
                Log.e("Error-url",call.request().url()+"  Error-Msg- "+t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
