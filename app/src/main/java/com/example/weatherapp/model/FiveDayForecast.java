package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * return from the weather api when retrieving the users 5 day forecast
 */
public class FiveDayForecast {
    private City city;
    @SerializedName("list")
    private ArrayList<Forecast> forecastList;

    public FiveDayForecast(City city, ArrayList<Forecast> forecastList) {
        this.city = city;
        this.forecastList = forecastList;
    }

    public FiveDayForecast() {
        forecastList = new ArrayList<>();
    }

    public City getCity() {
        return city;
    }

    public ArrayList<Forecast> getForecastList() {
        return forecastList;
    }

    public void setForecastList(ArrayList<Forecast> forecastList) {
        this.forecastList = forecastList;
    }
}
