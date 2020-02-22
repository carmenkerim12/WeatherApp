package com.example.weatherapp.CurrentWeather.mvp;

import com.example.weatherapp.helpers.LocationHelper;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;
import com.example.weatherapp.utils.Applog;

import static com.example.weatherapp.helpers.LocationHelper.TAG;

/**
 * this presenter is in associated with the {@link com.example.weatherapp.CurrentWeather.CurrentWeatherFragment}.
 * In charge of all the logic
 */

public class CurrentWeatherPresenter implements CurrentWeatherContract.Presenter {
    private CurrentWeatherContract.View view;
    private WeatherRepository weatherRepository;
    private LocationHelper locationHelper;

    public CurrentWeatherPresenter(CurrentWeatherContract.View view, WeatherRepository weatherRepository, LocationHelper locationHelper) {
        this.view = view;
        this.weatherRepository = weatherRepository;
        this.locationHelper = locationHelper;
    }

    @Override
    public void getUsersLocation() {
        view.weatherLoading();

        locationHelper.getUsersLastLocation(location -> {
            if (location != null) {
                LocationHelper.setUsersLocation(location.getLongitude(), location.getLatitude());

                // once we get the callback and set the location, we then want to make a call to get
                // the users weather
                getCurrentWeather();

                Applog.i(TAG, "onSuccess: " + location.getLongitude());
                Applog.i(TAG, "onSuccess: " + location.getLatitude());
            }
        });
    }

    @Override
    public void getCurrentWeather() {
        weatherRepository.getCurrentWeather(LocationHelper.lat, LocationHelper.lon, new WeatherDataProvider.NetworkResponse<Forecast>() {
            @Override
            public void onLoading() {
                view.weatherLoading();
            }

            @Override
            public void onSuccess(Forecast forecast) {
                view.weatherLoaded(forecast);
            }

            @Override
            public void onError(String error) {
                view.weatherError(error);
            }
        });
    }

    @Override
    public void onViewCreated() {
        getUsersLocation();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {

    }
}
