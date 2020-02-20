package com.example.weatherapp.model;


/**
 * @author carmenkerim
 * <p>
 * this class is nested in {@link Forecast model}
 * <p>
 */
public class Main {
    private float temp;
    private float pressure;
    private float humidity;

    public Main(float temp, float pressure, float humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    // Getter methods
    public float getTemp() {
        return temp;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }
}