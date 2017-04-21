package com.yousuf.android.moviesearch.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yousuf.android.moviesearch.BuildConfig;
import com.yousuf.android.moviesearch.R;
import com.yousuf.android.moviesearch.databinding.FragmentMovieDetailsBinding;
import com.yousuf.android.moviesearch.model.MovieInfo;
import com.yousuf.android.moviesearch.model.MovieList;

public class MovieDetailsFragment extends Fragment {
    public static final String TAG = MovieDetailsFragment.class.getSimpleName();
    private static final String MOVIE_INFO_KEY = "Movie-info-key";
    private FragmentMovieDetailsBinding movieDetailsBinding;
    private MovieInfo mMovieInfo;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    public static MovieDetailsFragment getInstance(MovieInfo movieInfo) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(MOVIE_INFO_KEY, movieInfo);
        fragment.setArguments(args);
        return fragment;
    }

//    public static MovieDetailsFragment getInstance(FragmentManager fm, MovieInfo movieInfo) {
//        MovieDetailsFragment fragment = (MovieDetailsFragment) fm.findFragmentByTag(TAG);
//        if (fragment == null) {
//            fragment = new MovieDetailsFragment();
//            Bundle args = new Bundle();
//            args.putSerializable(MOVIE_INFO_KEY, movieInfo);
//            fragment.setArguments(args);
//        }
//        return fragment;
//    }

    public void updateMovieInfo(MovieInfo movieInfo) {
        displayImage(movieInfo.getPoster_path(), movieInfo.getBackdrop_path());
        displayString(movieDetailsBinding.title, movieInfo.getTitle(), R.string.title_string);
        displayString(movieDetailsBinding.overview, movieInfo.getOverview(), R.string.overview_string);
        displayString(movieDetailsBinding.originalLanguage, movieInfo.getOriginal_language(), R.string.language_string);
        displayString(movieDetailsBinding.voteCount, String.valueOf(movieInfo.getVote_count()), R.string.vote_count_string);
        displayString(movieDetailsBinding.voteAverage, String.valueOf(movieInfo.getVote_average()), R.string.vote_average_string);
        displayString(movieDetailsBinding.popularity, String.valueOf(movieInfo.getPopularity()), R.string.popularity_string);
        displayString(movieDetailsBinding.releaseDate, movieInfo.getRelease_date(), R.string.releaseDate_string);
    }

    private void displayImage(String poster, String backdrop) {
        String path = poster;
        if (TextUtils.isEmpty(path)) {
            path = backdrop;
            if (TextUtils.isEmpty(path)) {
                movieDetailsBinding.poster.setImageResource(R.drawable.ic_no_image);
                return;
            }
        }
        Glide.with(getContext())
                .load(BuildConfig.MOVIE_POSTER_URL.concat(path))
                .error(R.drawable.ic_no_image)
                .crossFade(300)
                .into(movieDetailsBinding.poster);
    }

    private void displayString(TextView view, String value, int resId) {
        if (TextUtils.isEmpty(value)) {
            view.setVisibility(View.GONE);
            return;
        }
        view.setText(getString(resId, value));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovieInfo = (MovieInfo) getArguments().getSerializable(MOVIE_INFO_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        movieDetailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        return movieDetailsBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateMovieInfo(mMovieInfo);
    }

}
