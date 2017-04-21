package com.yousuf.android.moviesearch.presenter;

import android.text.TextUtils;

import com.yousuf.android.moviesearch.model.ErrorType;
import com.yousuf.android.moviesearch.model.MovieList;
import com.yousuf.android.moviesearch.utils.MovieSearchAdapter;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yousufsyed on 4/19/17.
 */
public class MovieInfoPresenter {

    private UIUpdateListener mListener;

    private MovieSearchAdapter mMovieSearchAdapter;

    public MovieInfoPresenter() {
        mMovieSearchAdapter = new MovieSearchAdapter();
    }

    public void setListener(UIUpdateListener listener) {
        mListener = listener;
    }

    public void removeListener(){
        mListener = null;
    }

    public void onInitSearch(String key, String page) {
        if(TextUtils.isEmpty(key)){
            showError(ErrorType.EMPTY_FIELD);
        } else {
            mMovieSearchAdapter.getMovieList(key, page)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MovieList>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            showError(ErrorType.NETWORK_REQUEST_FAILED);
                        }

                        @Override
                        public void onNext(MovieList posts) {
                            updateUI(posts);
                        }
                    });
        }
    }

    private void showError(ErrorType type){
        if(mListener != null){
            mListener.onError(type);
        }
    }

    private void updateUI(MovieList movieList){
        if(mListener != null){
            mListener.onSuccess(movieList);
        }
    }

    public interface UIUpdateListener {
        void onError(ErrorType type);
        void onSuccess(MovieList movieList);
    }



}
