package com.example.weatherapp.CurrentWeatherScreen.presenter;

import android.util.Log;

import com.example.weatherapp.CurrentWeatherScreen.CurrentWeatherContract;
import com.example.weatherapp.LocationHelper;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

import static com.example.weatherapp.LocationHelper.TAG;

public class CurrentWeatherPresenter implements CurrentWeatherContract.Presenter {
    private CurrentWeatherContract.View view;
    private WeatherRepository weatherRepository;
    private LocationHelper locationHelper;

    public CurrentWeatherPresenter(CurrentWeatherContract.View view, WeatherRepository weatherRepository, LocationHelper locationHelper) {
        this.view = view;
        this.weatherRepository = weatherRepository;
        this.locationHelper = locationHelper;
    }

    @Override
    public void getUsersLocation() {
        view.weatherLoading();

        locationHelper.getUsersLastLocation(location -> {
            if (location != null) {
                LocationHelper.setUsersLocation(location.getLongitude(), location.getLatitude());

                // once we get the callback and set the location, we then want to make a call to get
                // the users weather
                getUsersWeather();

                Log.i(TAG, "onSuccess: " + location.getLongitude());
                Log.i(TAG, "onSuccess: " + location.getLatitude());
            }
        });
    }

    @Override
    public void getUsersWeather() {
        weatherRepository.getCurrentWeather(LocationHelper.lat, LocationHelper.lon, new WeatherDataProvider.NetworkResponse() {
            @Override
            public void onLoading() {
                view.weatherLoading();
            }

            @Override
            public void onSuccess(Forecast forecast) {
                view.weatherLoaded(forecast);
            }

            @Override
            public void onError(String error) {
                view.weatherError(error);
            }
        });
    }

    @Override
    public void onViewCreated() {
        getUsersLocation();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {

    }
}
