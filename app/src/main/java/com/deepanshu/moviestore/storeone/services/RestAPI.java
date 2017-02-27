package com.deepanshu.moviestore.storeone.services;

import com.deepanshu.moviestore.storeone.models.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestAPI {

    @GET("/api/movies")
    Call<List<Movies>> getMyMovies();

    @GET("/api/movies/{id}")
    Call<Movies> getMovieInfo(@Path("id") String id);
}
