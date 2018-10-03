package com.hcl.androidweatherapp.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hcl.androidweatherapp.R;
import com.hcl.androidweatherapp.data.sharedpreferences.SharedPreference;
import com.hcl.androidweatherapp.model.WeatherData;
import com.hcl.androidweatherapp.presenter.MainPresenter;
import com.hcl.androidweatherapp.util.Constants;
import com.hcl.androidweatherapp.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherFragment extends Fragment implements WeatherFragmentView {
    @BindView(R.id.city)
    TextView mCityView;

    @BindView(R.id.temp)
    TextView mTempView;

    @BindView(R.id.mintemp)
    TextView mMinTempView;

    @BindView(R.id.maxtemp)
    TextView mMaxTempView;

    @BindView(R.id.root_container)
    LinearLayout mRootContainer;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.temp_image)
    ImageView mTempImage;

    MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        checkSharedPrefs();
        presenter = new MainPresenter(getActivity(), this);

        String city = getArguments().getString("city");

        loadWeatherFragment(city);
        return view;
    }

    private void loadWeatherFragment(String city) {
        presenter.fetchData(city);
        showProgress();
    }


    @Override
    public void showProgress() {
        Utils.showProgress(mProgressBar, getActivity().getWindow());
    }

    @Override
    public void hideProgress() {
        Utils.hideProgress(mProgressBar, getActivity().getWindow());
    }

    @Override
    public void checkSharedPrefs() {
        String city = SharedPreference.getPreference(getActivity(), Constants.CITY);
        if (!TextUtils.isEmpty(city)) {
            presenter.fetchData(city);
            showProgress();
        }
    }

    //Set Text Views with result
    @Override
    public void updateViews(WeatherData weatherData) {
        presenter.fetchImage(weatherData.getWeather().get(0).getIcon());
        mRootContainer.setVisibility(View.VISIBLE);
        mCityView.setText(weatherData.getName());
        mTempView.setText(getString(R.string.temp_string, String.valueOf(weatherData.getMain().getTemp())));
        mMinTempView.setText(getString(R.string.min_temp, String.valueOf(weatherData.getMain().getTemp_min())));
        mMaxTempView.setText(getString(R.string.max_temp, String.valueOf(weatherData.getMain().getTemp_max())));

    }

    @Override
    public void setImage(Bitmap responseObject) {
        mTempImage.setImageBitmap(responseObject);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}



