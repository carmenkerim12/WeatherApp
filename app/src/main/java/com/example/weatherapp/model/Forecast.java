package com.example.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * @author carmenkerim
 * <p>
 * parent response
 * <p>
 * this class is the whole object sent back from the weatherApi
 */
public class Forecast {
    private float id;
    private ArrayList<Weather> weather;
    private String name;
    private Main main;
    private Sys sys;
    @SerializedName("dt_txt")
    private String date;

    public Forecast(float id, ArrayList<Weather> weather, String name, Sys sys) {
        this.id = id;
        this.weather = weather;
        this.name = name;
        this.sys = sys;
    }

    // getter methods

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public String getDate() {
        return date;
    }
}
