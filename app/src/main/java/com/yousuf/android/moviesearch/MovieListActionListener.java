package com.yousuf.android.moviesearch;

import com.yousuf.android.moviesearch.model.MovieInfo;

/**
 * Created by yousufsyed on 4/21/17.
 */

public interface MovieListActionListener {
    void showMovieDetails(MovieInfo movieInfo);
    void getNextPage(String page);
}
