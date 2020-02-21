package com.example.weatherapp;

import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherContract;
import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherPresenter;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherPresenterTest {
    @Mock
    private CurrentWeatherContract.View view;

    @Mock
    private LocationHelper locationHelper;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private CurrentWeatherPresenter presenter;

    @Mock
    Forecast forecast;

    @Before
    public void setUp() {
        presenter = new CurrentWeatherPresenter(view, weatherRepository, locationHelper);
    }

    @Test
    public void onViewCreated(){
        presenter.getUsersLocation();
        Mockito.verify(view).weatherLoading();
    }

}
