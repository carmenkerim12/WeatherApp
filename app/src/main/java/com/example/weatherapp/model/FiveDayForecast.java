package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FiveDayForecast {
    private City city;
    @SerializedName("list")
    private ArrayList<Forecast> forecastList;

    public FiveDayForecast() {
        forecastList = new ArrayList<>();
    }

    public City getCity() {
        return city;
    }

    public ArrayList<Forecast> getForecastList() {
        return forecastList;
    }
}
