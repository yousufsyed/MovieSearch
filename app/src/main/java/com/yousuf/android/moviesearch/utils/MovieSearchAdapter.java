package com.yousuf.android.moviesearch.utils;

import com.yousuf.android.moviesearch.BuildConfig;
import com.yousuf.android.moviesearch.model.MovieList;
import com.yousuf.android.moviesearch.network.TheMovieApi;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;

/**
 * Created by yousufsyed on 4/20/17.
 */

public class MovieSearchAdapter {

    private Retrofit mRequest;

    private TheMovieApi mApi;

    public MovieSearchAdapter() {
        mRequest = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.MOVIE_BASE_URL)
                .build();
        mApi = mRequest.create(TheMovieApi.class);
    }

    public Observable<MovieList> getMovieList(String searchTerm, String page) {
        Map<String, String> params = new HashMap<>();
        params.put("query", searchTerm);
        params.put("page", page);
        params.put("include_adult", "false");
        params.put("api_key", BuildConfig.API_KEY);
        return mApi.getMovieList(params);
    }

    //https://api.themoviedb.org/3/search/movie/s9MOBcftSJn2lhCBTAnI8pMfLbJ.jpg
}
