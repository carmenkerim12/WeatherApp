package com.example.weatherapp.repository;

import com.example.weatherapp.model.FiveDayForecast;
import com.example.weatherapp.model.Forecast;

/**
 * Our WeatherRepository is gonna be our abstraction layer from our data provider which will
 * make the calls needed to retrieve the weather
 */
public class WeatherRepository {
    private WeatherDataProvider weatherDataProvider;

    // where we are going to store our fiveDayForecast when we retrieve it from the server
    // used specifically for our recyclerAdapter
    public static FiveDayForecast fiveDayForecast = new FiveDayForecast();

    public WeatherRepository(WeatherDataProvider weatherDataProvider) {
        this.weatherDataProvider = weatherDataProvider;
    }

    /**
     * used to communicate to our provider and get the users current weather
     *
     * @param lat             - latitude of the user
     * @param lon             - longitude of the user
     * @param networkResponse - our listener to communicate to the UI
     */
    public void getCurrentWeather(double lat, double lon, WeatherDataProvider.NetworkResponse<Forecast> networkResponse) {
        weatherDataProvider.getCurrentWeather(lat, lon, networkResponse);
    }

    /**
     * used to communicate to our provider and get the users 5 day weather forecast
     *
     * @param networkResponse
     */
    public void getFiveDayForecast(WeatherDataProvider.NetworkResponse<FiveDayForecast> networkResponse) {
        weatherDataProvider.getFiveDayForecast(networkResponse);
    }

    public static FiveDayForecast getFiveDayForecast() {
        return fiveDayForecast;
    }

    public static void setFiveDayForecast(FiveDayForecast fiveDayForecast) {
        WeatherRepository.fiveDayForecast = fiveDayForecast;
    }
}
