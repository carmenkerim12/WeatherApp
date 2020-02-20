package com.example.weatherapp.model;

/**
 * @author carmenkerim
 * <p>
 * this class is nested in {@link Forecast model}
 *
 * We can utilize this class for any coord info regarding the users location
 * <p>
 */
public class Coord {
    private float lon;
    private float lat;

    public Coord(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    // getter methods

    public float getLon() {
        return lon;
    }

    public float getLat() {
        return lat;
    }
}