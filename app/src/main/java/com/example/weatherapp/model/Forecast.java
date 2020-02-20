package com.example.weatherapp.model;

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
    private Sys sys;

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
}
