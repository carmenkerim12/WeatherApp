package com.example.weatherapp.model;

public class City {
    private float id;
    private String name;
    Coord coord;
    private String country;

    public float getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
