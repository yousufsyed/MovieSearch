package com.yousuf.android.moviesearch.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yousuf.android.moviesearch.MovieListActionListener;
import com.yousuf.android.moviesearch.adapters.MovieListAdapter;
import com.yousuf.android.moviesearch.MovieListCallback;
import com.yousuf.android.moviesearch.R;
import com.yousuf.android.moviesearch.model.MovieInfo;
import com.yousuf.android.moviesearch.model.MovieList;

import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment implements MovieListCallback {

    public static final String TAG = MovieListFragment.class.getSimpleName();

    private TextView mErrorMessage;
    private RecyclerView mList;
    private MovieListActionListener mListener;

    private List<MovieInfo> movieInfoList;

    public MovieListFragment() {
        // Required empty public constructor
    }

    public static MovieListFragment getInstance(FragmentManager fm) {
        MovieListFragment fragment = (MovieListFragment) fm.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new MovieListFragment();
        }
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        movieInfoList = new ArrayList<>();
        if (context instanceof MovieListActionListener) {
            mListener = (MovieListActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private boolean mIsLoading = false;
    private int currentPage = 1;
    private int totalPages = 1;

    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (mIsLoading)
                return;
            LinearLayoutManager layoutMgr = (LinearLayoutManager) recyclerView.getLayoutManager();
            int visibleItemCount = layoutMgr.getChildCount();
            int totalItemCount = layoutMgr.getItemCount();
            int pastVisibleItems = layoutMgr.findFirstVisibleItemPosition();
            if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                //End of list
                if(currentPage < totalPages) {
                    mIsLoading = true;
                    loadNextPage();
                }
            }
        }
    };

    private void initView(View root) {
        mErrorMessage = (TextView) root.findViewById(R.id.empty_view);
        mList = (RecyclerView) root.findViewById(R.id.movie_list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        final MovieListAdapter mAdapter = new MovieListAdapter();
        mAdapter.setListener(this);
        mAdapter.setHasStableIds(false);
        mList.setLayoutManager(manager);
        mList.setAdapter(mAdapter);
        mList.addOnScrollListener(mScrollListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_movie_list, container, false);
        initView(root);
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (movieInfoList != null) {
            mErrorMessage.setVisibility(View.GONE);
            mList.setVisibility(View.VISIBLE);
            ((MovieListAdapter) mList.getAdapter()).updateList(movieInfoList);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMovieSelected(MovieInfo movieInfo) {
        if (mListener != null) {
            mListener.showMovieDetails(movieInfo);
        }
    }

    private void loadNextPage(){
        if (mListener != null) {
            mListener.getNextPage(String.valueOf(++currentPage));
        }
    }

    public void resetList() {
        if(movieInfoList != null) {
            movieInfoList.clear();
        }
    }

    public void showMovieList(MovieList movieList) {
        if (movieList == null || movieList.getResults() == null || movieList.getResults().isEmpty()) {
            showErrorMessage(getString(R.string.no_results_found));
        } else {
            mIsLoading = false;
            mErrorMessage.setVisibility(View.GONE);
            mList.setVisibility(View.VISIBLE);
            currentPage = movieList.getPage();
            totalPages = movieList.getTotal_pages();
            movieInfoList.addAll(movieList.getResults());
            ((MovieListAdapter) mList.getAdapter()).updateList(movieInfoList);
        }
    }

    public void showErrorMessage(String errorMessage) {
        mIsLoading = false;
        mErrorMessage.setText(errorMessage);
        mErrorMessage.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
        mList.setVisibility(View.GONE);
    }

}
