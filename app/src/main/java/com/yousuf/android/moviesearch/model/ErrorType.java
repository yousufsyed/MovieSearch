package com.yousuf.android.moviesearch.model;

import com.yousuf.android.moviesearch.R;

/**
 * Created by yousufsyed on 4/20/17.
 */
public enum ErrorType {

    NO_NETWORK (100, R.string.network_unavailable),
    NETWORK_REQUEST_FAILED (100, R.string.network_request_failed),
    EMPTY_FIELD (101, R.string.empty_field);

    private int mCode;
    private int mMessageId;

    ErrorType(int code, int messageId){
        mCode = code;
        mMessageId = messageId;
    }

    public int getMessageId(){
        return mMessageId;
    }
}