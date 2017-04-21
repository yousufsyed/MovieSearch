package com.yousuf.android.moviesearch.network;

import com.yousuf.android.moviesearch.model.MovieList;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by yousufsyed on 4/20/17.
 */
public interface TheMovieApi {

    @GET("search/movie?")
    Observable<MovieList> getMovieList(@QueryMap Map<String, String> params);

}
