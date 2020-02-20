package com.example.weatherapp.model;

/**
 * @author carmenkerim
 * <p>
 * this class is nested in {@link Forecast model}
 * <p>
 * this model is for any wind info
 */
public class Wind {
    private float speed;
    private float deg;

    public Wind(float speed, float deg) {
        this.speed = speed;
        this.deg = deg;
    }

    // getter methods

    public float getSpeed() {
        return speed;
    }

    public float getDeg() {
        return deg;
    }

    // setter methods

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }
}