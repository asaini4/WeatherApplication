package com.hcl.androidweatherapp.ui;

import android.graphics.Bitmap;

import com.hcl.androidweatherapp.model.WeatherData;


public interface WeatherFragmentView {

    void showProgress();
    void hideProgress();
    void checkSharedPrefs();
    void updateViews(WeatherData weatherData);
    void setImage(Bitmap responseObject);
}
