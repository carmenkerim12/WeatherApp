package com.example.weatherapp.model;


/**
 * @author carmenkerim
 * <p>
 * this class is nested in {@link Forecast model}
 * <p>
 */
public class Main {
    private float temp;

    public Main(float temp) {
        this.temp = temp;
    }

    // Getter methods
    public float getTemp() {
        return temp;
    }
}