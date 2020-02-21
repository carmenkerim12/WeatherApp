package com.example.weatherapp.CurrentWeatherScreen.presenter;

import com.example.weatherapp.CurrentWeatherScreen.CurrentWeatherContract;
import com.example.weatherapp.LocationHelper;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

public class CurrentWeatherPresenter implements CurrentWeatherContract.Presenter {
    private CurrentWeatherContract.View view;
    private WeatherRepository weatherRepository;

    public CurrentWeatherPresenter(CurrentWeatherContract.View view, WeatherRepository weatherRepository) {
        this.view = view;
        this.weatherRepository = weatherRepository;
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

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {

    }
}
