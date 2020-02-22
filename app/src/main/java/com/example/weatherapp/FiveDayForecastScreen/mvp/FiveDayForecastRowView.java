package com.example.weatherapp.FiveDayForecastScreen.mvp;

public interface FiveDayForecastRowView {
    void setDescription(String description);

    void setDate(String date);

    void setImage(String url);

    void setTemperature(String temperature);
}
