package com.example.weatherapp.CurrentWeather.mvp;

import com.example.weatherapp.BasePresenter;
import com.example.weatherapp.BaseView;
import com.example.weatherapp.model.Forecast;

/**
 * this defines the contract of how the presenter and view will communicate within the fragment
 * <p>
 * {@link com.example.weatherapp.CurrentWeather.CurrentWeatherFragment}.
 */
public interface CurrentWeatherContract {
    interface Presenter extends BasePresenter {
        /**
         * this will be used to make a call to our  {@link com.example.weatherapp.helpers.LocationHelper}
         * to get the users location
         */
        void getUsersLocation();

        /**
         * this will be used to make a call to our  {@link com.example.weatherapp.repository.WeatherRepository}
         * to get the current weather
         */
        void getCurrentWeather();

        void onStart();
    }

    interface View extends BaseView<Presenter> {
        /**
         * this will set the name of the city
         *
         * @param text - cities name
         */
        void setCityName(String text);

        /**
         * this will set the description of weather
         *
         * @param text - description i.e clear sky
         */
        void setWeatherDescription(String text);

        /**
         * this will set the temperature of the users current location
         *
         * @param text - users temp in Fahrenheit
         */
        void setTemp(String text);

        /**
         * we call this whenever we are making a network call and we are waiting on the data.
         * This lets us know when to show a progressBar
         */
        void weatherLoading();

        /**
         * once we get the weather from the api we want to display the proper views and get rid of
         * anything prior. i.e progress bar
         *
         * @param forecast - this forecast will give us the information
         *                 to display the city, temp, and weather description
         */
        void weatherLoaded(Forecast forecast);

        /**
         * if for some reason there is an error communicating to the api, we want to be able to handle that
         * and display it to the user
         *
         * @param error - any error message we might want to display
         */
        void weatherError(String error);

        /**
         * when the user clicks on the Five Day forecast button, we want to navigate them
         */
        void onNavigateToFiveDayForecast();

        /**
         * this is used to either hide content and shower progressBar or hide progressBar and show content
         *
         * @param isWeatherLoading - whether we are still getting content and need to show the progressBar
         */
        void toggleContentVisibility(boolean isWeatherLoading);

        void locationError();
    }
}
