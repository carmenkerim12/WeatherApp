package com.example.weatherapp.CurrentWeather.mvp;

import com.example.weatherapp.BasePresenter;
import com.example.weatherapp.BaseView;
import com.example.weatherapp.model.Forecast;

public interface CurrentWeatherContract {
    interface Presenter extends BasePresenter {
        void getUsersLocation();
        void getUsersWeather();
    }

    interface View extends BaseView<Presenter> {
        void setCityName(String text);

        void setWeatherDescription(String text);

        void setTemp(String text);

        void weatherLoading();

        void weatherLoaded(Forecast forecast);

        void weatherError(String error);
        
    }
}
