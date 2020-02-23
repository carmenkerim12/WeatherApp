package com.example.weatherapp;

import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherContract;
import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherPresenter;
import com.example.weatherapp.helpers.LocationHelper;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * testing class for our CurrentWeatherPresenterTest
 */
@RunWith(MockitoJUnitRunner.class)
public class CurrentWeatherPresenterTest {
    @Mock
    private LocationHelper locationHelper;

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private CurrentWeatherContract.View view;

    @Mock
    private CurrentWeatherPresenter presenter;

    @Captor
    private ArgumentCaptor<WeatherDataProvider.NetworkResponse<Forecast>> networkResponseArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CurrentWeatherPresenter(view, weatherRepository, locationHelper);
    }

    /**
     * useful to check when we fetch the current weather during a successful use case.
     * Our presenter should correspond correctly calling the correct views
     */
    @Test
    public void getCurrentWeather_WeatherLoadedSuccess() {
        presenter.getCurrentWeather();
        verify(weatherRepository).getCurrentWeather(Mockito.eq(LocationHelper.lat), Mockito.eq(LocationHelper.lon), networkResponseArgumentCaptor.capture());
        networkResponseArgumentCaptor.getValue().onLoading();
        verify(view).weatherLoading();
        final Forecast forecast = new Forecast();
        networkResponseArgumentCaptor.getValue().onSuccess(forecast);
        verify(view).weatherLoaded(forecast);
    }

    /**
     * useful to check when we fetch the current weather during an alternative use case.
     * Our presenter should correspond correctly calling the correct views
     */
    @Test
    public void getCurrentWeather_WeatherError() {
        presenter.getCurrentWeather();
        verify(weatherRepository).getCurrentWeather(Mockito.eq(LocationHelper.lat), Mockito.eq(LocationHelper.lon), networkResponseArgumentCaptor.capture());
        networkResponseArgumentCaptor.getValue().onLoading();
        verify(view).weatherLoading();
        final String errorMsg = "something went wrong";
        networkResponseArgumentCaptor.getValue().onError(errorMsg);
        verify(view).weatherError(errorMsg);
    }


}
