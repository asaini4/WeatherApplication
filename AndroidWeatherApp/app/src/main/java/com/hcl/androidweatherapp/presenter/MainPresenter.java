package com.hcl.androidweatherapp.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;
import com.android.volley.Request;
import com.google.gson.Gson;
import com.hcl.androidweatherapp.app.NetworkRequest;
import com.hcl.androidweatherapp.model.WeatherData;
import com.hcl.androidweatherapp.ui.WeatherFragmentView;
import com.hcl.androidweatherapp.util.Constants;

public class MainPresenter {
    private WeatherFragmentView mView;
    private Context context;
    private NetworkRequest networkRequest;

    /* The request completed listener */
    private NetworkRequest.OnRequestCompletedListener requestCompletedListener = new NetworkRequest.OnRequestCompletedListener() {
        @Override
        public void onRequestCompleted(String requestName, boolean status, String response, String errorMessage) {
            if (mView != null) {
                mView.hideProgress();
                if (status) {
                    WeatherData weatherData = new Gson().fromJson(response, WeatherData.class);
                    mView.updateViews(weatherData);
                }
            }

        }
    };

    /* The Image request completed listener */
    private NetworkRequest.OnImageRequestListener imageRequestCompletedListener = new NetworkRequest.OnImageRequestListener() {
        @Override
        public void onImageRequestCompleted(String requestName, boolean status, Bitmap response, String errorMessage) {
            if (mView != null) {
                mView.hideProgress();
                if (status) {
                    mView.setImage(response);
                }
            }


        }
    };

    public MainPresenter(Context context, WeatherFragmentView view) {
        this.context = context;
        this.mView = view;
        networkRequest = new NetworkRequest(context, requestCompletedListener, imageRequestCompletedListener);
    }

    //API Success
    public void fetchData(String city) {
        Uri uri  = Uri.parse(Constants.OPEN_WEATHER_API).buildUpon()
                .appendQueryParameter(Constants.WEATHER_FORECAST_PARAM, city)
                .appendQueryParameter(Constants.UNITS_PARAM, Constants.METRICS_PARAM)
                .appendQueryParameter(Constants.APP_ID_PARAM, Constants.APP_ID)
                .build();
        mView.showProgress();
        networkRequest.requestString(uri.toString(), null, Request.Method.GET);
    }

    //API Error
    public void fetchImage(String imageId) {
        Uri uri  = Uri.parse(Constants.OPEN_WEATHER_IMAGE_API).buildUpon()
                .appendPath(imageId + Constants.PNG_EXTENSION)
                .build();
        mView.showProgress();
        networkRequest.requestImage(uri.toString(), 300, 200, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888);
    }

    public void onDestroy() {
        if (mView != null) {
            mView = null;
        }
    }

}
