package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * return from the weather api when retrieving the users 5 day forecast
 */
public class FiveDayForecast {
    @SerializedName("list")
    private ArrayList<Forecast> forecastList;

    public FiveDayForecast() {
        forecastList = new ArrayList<>();
    }

    public ArrayList<Forecast> getForecastList() {
        return forecastList;
    }
}
