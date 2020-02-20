package com.example.weatherapp.model;

/**
 * @author carmenkerim
 * <p>
 * this class is nested in {@link Forecast model}
 * <p>
 * Primarly used to get the country of the user
 */
public class Sys {
    private String country;

    public Sys(String country) {
        this.country = country;
    }

    // getter methods
    public String getCountry() {
        return country;
    }
}
