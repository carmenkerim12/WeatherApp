package com.example.weatherapp.CurrentWeather.mvp;

import android.location.Location;
import android.os.Bundle;

import com.example.weatherapp.helpers.LocationHelper;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

/**
 * this presenter is in associated with the {@link com.example.weatherapp.CurrentWeather.CurrentWeatherFragment}.
 * In charge of all the logic
 */

public class CurrentWeatherPresenter implements CurrentWeatherContract.Presenter, android.location.LocationListener {
    private CurrentWeatherContract.View view;
    private WeatherRepository weatherRepository;
    private LocationHelper locationHelper;

    public CurrentWeatherPresenter(CurrentWeatherContract.View view, WeatherRepository weatherRepository, LocationHelper locationHelper) {
        this.view = view;
        this.weatherRepository = weatherRepository;
        this.locationHelper = locationHelper;
    }

    @Override
    public void getCurrentWeather() {
        weatherRepository.getCurrentWeather(LocationHelper.lat, LocationHelper.lon, new WeatherDataProvider.NetworkResponse<Forecast>() {
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
    public void onStart() {

    }

    @Override
    public void getUsersLocation() {
        final String errorMsgLocation = "Something went wrong with your location";
        view.weatherLoading();

        locationHelper.getUsersLastLocation(new LocationHelper.LocationResponse() {
            @Override
            public void onSuccess() {
                getCurrentWeather();
            }

            @Override
            public void onError() {
                view.locationError();
            }
        }, this);
    }

    @Override
    public void onViewCreated() {
        getUsersLocation();
    }

    @Override
    public void onDestroy() {
        locationHelper.locationManager.removeUpdates(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            LocationHelper.setUsersLocation(location.getLongitude(), location.getLatitude());
            getCurrentWeather();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
