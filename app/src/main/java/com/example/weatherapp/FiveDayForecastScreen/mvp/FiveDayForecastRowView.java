package com.example.weatherapp.FiveDayForecastScreen.mvp;

public interface FiveDayForecastRowView {
    /**
     * set the description of the weather. I.e clear sky
     *
     * @param description - i.e scattered clouds
     */
    void setDescription(String description);

    /**
     * the date of the forecast
     *
     * @param date
     */
    void setDate(String date);

    /**
     * sets image from api
     *
     * @param url - url for the png
     */
    void setImage(String url);

    /**
     * sets temperature in Fahrenheit
     *
     * @param temperature - temperature in fahrenheit
     */
    void setTemperature(String temperature);
}
