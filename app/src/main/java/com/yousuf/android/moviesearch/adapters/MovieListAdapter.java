package com.yousuf.android.moviesearch.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yousuf.android.moviesearch.BuildConfig;
import com.yousuf.android.moviesearch.MovieListCallback;
import com.yousuf.android.moviesearch.R;
import com.yousuf.android.moviesearch.model.MovieInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yousufsyed on 4/20/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ItemHolder> {

    private List<MovieInfo> mMovieList;
    private MovieListCallback mListener;

    public MovieListAdapter(){
        mMovieList = new ArrayList<>();
    }

    public void updateList(List<MovieInfo> movieInfoList){
        mMovieList = movieInfoList;
        notifyDataSetChanged();
    }

    public void resetList(){
        mMovieList.clear();
        notifyDataSetChanged();
    }

    public void setListener(MovieListCallback listener){
        mListener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        MovieInfo info = mMovieList.get(position);
        holder.mTitle.setText(info.getTitle());
        holder.mSubTitle.setText(info.getOverview());
        displayImage(holder.mImageView,  info.getPoster_path(), info.getBackdrop_path());
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    private void displayImage(ImageView image, String poster, String backdrop){
        String path = poster;
        if (TextUtils.isEmpty(path)) {
            path = backdrop;
            if (TextUtils.isEmpty(path)) {
                image.setImageResource(R.drawable.ic_no_image);
                return;
            }
        }
        Glide.with(image.getContext())
                .load(BuildConfig.MOVIE_THUMBNAIL_URL.concat(path))
                .error(R.drawable.ic_no_image)
                .crossFade(300)
                .into(image);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTitle, mSubTitle;

        ItemHolder(View view){
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.image);
            mTitle = (TextView) view.findViewById(R.id.title);
            mSubTitle = (TextView) view.findViewById(R.id.summary);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                MovieInfo info = mMovieList.get(getAdapterPosition());
                mListener.onMovieSelected(info);
            }
        }
    }
}
