package com.yousuf.android.moviesearch.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yousufsyed on 4/20/17.
 */

public class MovieInfo implements Serializable {
    /**
     * poster_path : /s9MOBcftSJn2lhCBTAnI8pMfLbJ.jpg
     * adult : false
     * overview : Action thriller featuring a dramatic race against time and death. After 10 billion won worth of drugs mysteriously disappear, police officer Kang Sung Joo (Ko Su) is faced with the unenviable task of tracking down the perpetrators of the clever coup. During his risky mission for justice, he stumbles into news reporter Seo Yoo Jin (Song Ji-hyo) who has the gift to foresee the future. To his shock, Kang learns that he will be murdered within 24 hours.
     * release_date : 2004-10-22
     * genre_ids : [28,80,9648]
     * id : 19793
     * original_title : 썸
     * original_language : en
     * title : Some
     * backdrop_path : null
     * popularity : 1.002748
     * vote_count : 4
     * video : false
     * vote_average : 4.8
     */

    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private int id;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;
    private List<Integer> genre_ids;

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }
}
