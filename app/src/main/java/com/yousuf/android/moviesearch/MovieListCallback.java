package com.yousuf.android.moviesearch;

import com.yousuf.android.moviesearch.model.MovieInfo;

/**
 * Created by yousufsyed on 4/20/17.
 */

public interface MovieListCallback {
    void onMovieSelected(MovieInfo movieInfo);
}
