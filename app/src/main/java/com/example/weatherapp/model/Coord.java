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
    private double lon;
    private double lat;

    public Coord(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    // getter methods

    public double getLon() {
        return lon;
    }

    public double getLat() {
        return lat;
    }


    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}