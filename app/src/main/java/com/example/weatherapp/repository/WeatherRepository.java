package com.example.weatherapp.repository;

/**
 * Our WeatherRepository is gonna be our abstraction layer from our data provider which will
 * make the calls needed to retrieve the weather
 */
public class WeatherRepository {
    WeatherDataProvider weatherDataProvider;

    public WeatherRepository(WeatherDataProvider weatherDataProvider) {
        this.weatherDataProvider = weatherDataProvider;
    }

    public void getCurrentWeather(double lat, double lon, WeatherDataProvider.NetworkResponse networkResponse) {
        weatherDataProvider.getCurrentWeather(lat, lon, networkResponse);
    }
}
