package com.yousuf.android.moviesearch.model;

import java.util.List;

/**
 * Created by yousufsyed on 4/20/17.
 */

public class MovieList {
    /**
     * page : 1
     * results : [{"poster_path":"/s9MOBcftSJn2lhCBTAnI8pMfLbJ.jpg","adult":false,"overview":"Action thriller featuring a dramatic race against time and death. After 10 billion won worth of drugs mysteriously disappear, police officer Kang Sung Joo (Ko Su) is faced with the unenviable task of tracking down the perpetrators of the clever coup. During his risky mission for justice, he stumbles into news reporter Seo Yoo Jin (Song Ji-hyo) who has the gift to foresee the future. To his shock, Kang learns that he will be murdered within 24 hours.","release_date":"2004-10-22","genre_ids":[28,80,9648],"id":19793,"original_title":"썸","original_language":"en","title":"Some","backdrop_path":null,"popularity":1.002748,"vote_count":4,"video":false,"vote_average":4.8},{"poster_path":"/pxc9EFCMYkItESpqqrI783yl8Gh.jpg","adult":false,"overview":"Two musicians witness a mob hit and struggle to find a way out of the city before they are found by the gangsters. Their only opportunity is to join an all-girl band as they leave on a tour. To make their getaway they must first disguise themselves as women, then keep their identities secret and deal with the problems this brings - such as an attractive bandmate and a very determined suitor.","release_date":"1959-03-18","genre_ids":[35,10749],"id":239,"original_title":"Some Like It Hot","original_language":"en","title":"Some Like It Hot","backdrop_path":"/sdibVacEUaYcM41zmepM0J2xNqq.jpg","popularity":2.69333,"vote_count":567,"video":false,"vote_average":7.9},{"poster_path":"/mIpd0rGxruvxCnMSmh4gd8wuhmv.jpg","adult":false,"overview":"A comedy that follows a group of friends as they navigate their way through the freedoms and responsibilities of unsupervised adulthood.","release_date":"2016-03-30","genre_ids":[35],"id":295699,"original_title":"Everybody Wants Some!!","original_language":"en","title":"Everybody Wants Some!!","backdrop_path":"/fmeDmitXrXVfibaBo5aRNECMU81.jpg","popularity":2.680364,"vote_count":324,"video":false,"vote_average":6.3}]
     * total_results : 651
     * total_pages : 33
     */

    private int page;
    private int total_pages;
    private int total_results;
    private List<MovieInfo> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<MovieInfo> getResults() {
        return results;
    }

    public void setResults(List<MovieInfo> results) {
        this.results = results;
    }

}
