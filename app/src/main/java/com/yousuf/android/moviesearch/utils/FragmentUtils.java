package com.yousuf.android.moviesearch.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.yousuf.android.moviesearch.R;
import com.yousuf.android.moviesearch.fragment.MovieDetailsFragment;
import com.yousuf.android.moviesearch.fragment.MovieListFragment;
import com.yousuf.android.moviesearch.model.MovieInfo;

/**
 * Created by yousufsyed on 4/19/17.
 */

public class FragmentUtils {

    /**
     * @param fm
     * @param containerId
     */
    public static void addMovieListFragment(FragmentManager fm, int containerId) {
        Fragment fragment = MovieListFragment.getInstance(fm);
        addFragment(fm, fragment, containerId, MovieListFragment.TAG);
    }

    public static void addMovieDetailsFragment(FragmentManager fm, int containerId, MovieInfo movieInfo) {
//        MovieDetailsFragment fragment = MovieDetailsFragment.getInstance(fm, movieInfo);
        MovieDetailsFragment fragment = MovieDetailsFragment.getInstance(movieInfo);
        addFragment(fm, fragment, containerId, MovieDetailsFragment.TAG);
    }

    /**
     * @param fm
     * @param containerId
     */
    public static void gotoMovieDetailsFragment(FragmentManager fm, int containerId, MovieInfo movieInfo) {
//        MovieDetailsFragment fragment = MovieDetailsFragment.getInstance(fm, movieInfo);
        MovieDetailsFragment fragment = MovieDetailsFragment.getInstance(movieInfo);
        if(fragment.isAdded() && fragment.isVisible()){
            fragment.updateMovieInfo(movieInfo);
        } else {
            replaceFragment(fm, fragment, containerId, MovieDetailsFragment.TAG);
        }
    }

    /**
     * Add Fragment without adding to backstack.
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param fragmentName
     */
    public static void addFragment(FragmentManager fm, Fragment fragment,
                                    int containerId, String fragmentName) {
        if (fragment != null) {
            fm.beginTransaction()
                    .replace(containerId, fragment, fragmentName)
                    .commit();
        }
    }

    /**
     * Replace fragment and add to backstack.
     *
     * @param fm
     * @param fragment
     * @param containerId
     * @param fragmentName
     */
    public static void replaceFragment(FragmentManager fm, Fragment fragment,
                                        int containerId, String fragmentName) {
        if (fragment != null) {
            fm.beginTransaction()
                    .replace(containerId, fragment, fragmentName)
                    .addToBackStack(fragmentName)
                    .commit();
        }
    }

    public static void resetFragmentStack(FragmentManager fm) {
        if(isBackStackAvailable(fm)){
            fm.popBackStackImmediate();
        }
    }

    public static MovieListFragment getMovieListToFront(FragmentManager fm) {
        MovieListFragment fragment = MovieListFragment.getInstance(fm);
        if (fragment.isAdded()) {
            if (!fragment.isVisible() && isBackStackAvailable(fm)) {
                fm.popBackStackImmediate();
            }
        } else {
            FragmentUtils.addFragment(fm, fragment, R.id.fragment_container, MovieListFragment.TAG);
        }
        return fragment;
    }

    public static boolean isBackStackAvailable(FragmentManager fm) {
        int fragmentCount = fm.getBackStackEntryCount();
        return (fragmentCount > 0);
    }
}
