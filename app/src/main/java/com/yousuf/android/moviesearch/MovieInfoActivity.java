package com.yousuf.android.moviesearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yousuf.android.moviesearch.model.ErrorType;
import com.yousuf.android.moviesearch.model.MovieInfo;
import com.yousuf.android.moviesearch.model.MovieList;
import com.yousuf.android.moviesearch.presenter.MovieInfoPresenter;
import com.yousuf.android.moviesearch.utils.AppUtils;
import com.yousuf.android.moviesearch.utils.FragmentUtils;

public class MovieInfoActivity extends AppCompatActivity implements
        MovieListActionListener,TextView.OnEditorActionListener,
        MovieInfoPresenter.UIUpdateListener {

    private MovieInfoPresenter mPresenter;
    private ProgressBar mProgress;
    private EditText mSearchTerm;
    private TextView mSearchTitle;
    private Button mDisplayButton, mSubmit;
    private LinearLayout mSearchContainer, mSubmitContainer;
    private static final int ANIM_DURATION = 300;
    private String currentWord = "";
    private boolean isTablet;

    private void initViews() {
        mSearchContainer = (LinearLayout) findViewById(R.id.search_container);
        mSubmitContainer = (LinearLayout) findViewById(R.id.submit_container);
        mSubmitContainer.setAlpha(0);
        mSearchTitle = (TextView) findViewById(R.id.results_title);
        mSearchTerm = (EditText) findViewById(R.id.search_term);
        mDisplayButton = (Button) findViewById(R.id.btn_search);
        mSubmit = (Button) findViewById(R.id.btn_submit);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        mProgress.setVisibility(View.GONE);

        mSearchTerm.setOnEditorActionListener(this);
    }

    public void onSearchClicked(View view) {
        mSearchContainer.animate().alpha(0).setDuration(ANIM_DURATION).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        mSearchContainer.setVisibility(View.GONE);
                        mSubmitContainer.setVisibility(View.VISIBLE);
                        mSubmitContainer.animate().alpha(1).setDuration(ANIM_DURATION);
                        mDisplayButton.setEnabled(false);
                        mSubmit.setEnabled(true);
                    }
                }
        );
    }

    public void onSubmitClicked(View view) {
        initSearch();
    }

    private void initSearch() {
        mSubmitContainer.animate().alpha(0).setDuration(ANIM_DURATION).withEndAction(
                new Runnable() {
                    @Override
                    public void run() {
                        AppUtils.hideKeyboard(getApplicationContext(), mSearchTerm);
                        clearSearchData();
                        mSubmitContainer.setVisibility(View.GONE);
                        String term = mSearchTerm.getText().toString().trim();
                        mSearchTitle.setText(getString(R.string.results_for, term));
                        getList(term, "1");
                        mSearchContainer.setVisibility(View.VISIBLE);
                        mSearchContainer.animate().alpha(1).setDuration(ANIM_DURATION);
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mPresenter = new MovieInfoPresenter();
        initViews();
        isTablet = getResources().getBoolean(R.bool.isTablet);
        FragmentUtils.resetFragmentStack(getSupportFragmentManager());
        if (savedInstanceState == null) {
            FragmentUtils.addMovieListFragment(getSupportFragmentManager(), R.id.fragment_container);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.setListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.removeListener();
    }

    @Override
    protected void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (FragmentUtils.isBackStackAvailable(getSupportFragmentManager())) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onError(ErrorType type) {
        mDisplayButton.setEnabled(true);
        mProgress.setVisibility(View.GONE);
        FragmentUtils.getMovieListToFront(getSupportFragmentManager()).showErrorMessage(getString(type.getMessageId()));
    }

    @Override
    public void onSuccess(MovieList movieList) {
        if (isTablet) {
            FragmentUtils.addMovieDetailsFragment(getSupportFragmentManager(),
                    R.id.fragment_details_container, movieList.getResults().get(0));
        }
        FragmentUtils.getMovieListToFront(getSupportFragmentManager()).showMovieList(movieList);
        mProgress.setVisibility(View.GONE);
        mDisplayButton.setEnabled(true);
    }

    private void clearSearchData() {
        FragmentUtils.getMovieListToFront(getSupportFragmentManager()).resetList();
    }

    @Override
    public void showMovieDetails(MovieInfo movieInfo) {
        int id = R.id.fragment_container;
        if(isTablet) {
            id = R.id.fragment_details_container;
        }
        FragmentUtils.gotoMovieDetailsFragment(getSupportFragmentManager(), id, movieInfo);
    }

    public void getNextPage(String page) {
        getList(currentWord, page);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            initSearch();
            return true;
        }
        return false;
    }

    public void getList(String term, String page) {
        mProgress.setVisibility(View.VISIBLE);
        mDisplayButton.setEnabled(false);
        mSubmit.setEnabled(false);
        currentWord = term;
        mPresenter.onInitSearch(term, page);
    }
}
