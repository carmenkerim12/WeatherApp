package com.example.weatherapp.repository;

import android.content.Context;

/**
 * Our WeatherRepository is gonna be our abstraction layer from our data provider which will
 * make the calls needed to retrieve the weather
 */
public class WeatherRepository {
    WeatherDataProvider weatherDataProvider;

    public WeatherRepository(Context context) {
        this.weatherDataProvider = new WeatherDataProvider(context);
    }

    public void getCurrentWeather(double lat, double lon, WeatherDataProvider.NetworkResponse networkResponse) {
        weatherDataProvider.getCurrentWeather(lat, lon, networkResponse);
    }
}
