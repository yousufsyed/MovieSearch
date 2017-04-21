package com.yousuf.android.moviesearch.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yousuf.android.moviesearch.R;

/**
 * Created by yousufsyed on 4/19/17.
 */

public class AppUtils {

    private static final String TAG = AppUtils.class.getSimpleName();

    public static boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnectedOrConnecting();
            }
        } catch (Exception ex) {
            Log.d(TAG, "Error checking network connectivity: ", ex);
        }
        return false;
    }

    public static void hideKeyboard(Context ctx, View view){
        InputMethodManager imm = (InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
