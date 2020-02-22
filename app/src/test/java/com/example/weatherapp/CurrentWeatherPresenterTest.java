package com.example.weatherapp;

import android.location.Location;

import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherContract;
import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherPresenter;
import com.example.weatherapp.helpers.LocationHelper;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;
import com.google.android.gms.tasks.OnSuccessListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

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

    @Captor
    private ArgumentCaptor<OnSuccessListener<Location>> onSuccessListenerArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new CurrentWeatherPresenter(view, weatherRepository, locationHelper);
    }

//    @Test
//    public void getUsersLocation() {
//        presenter.getUsersLocation();
//        verify(view).weatherLoading();
//        locationHelper.getUsersLastLocation(onSuccessListenerArgumentCaptor.capture());
//        ArgumentCaptor<Location> location = ArgumentCaptor.forClass(Location.class);
//    }

    @Test
    public void getCurrentWeather_WeatherLoaded() {
        presenter.getCurrentWeather();
        verify(weatherRepository).getCurrentWeather(Mockito.eq(LocationHelper.lat), Mockito.eq(LocationHelper.lon), networkResponseArgumentCaptor.capture());
        networkResponseArgumentCaptor.getValue().onLoading();
        verify(view).weatherLoading();
        final Forecast forecast = new Forecast();
        networkResponseArgumentCaptor.getValue().onSuccess(forecast);
        verify(view).weatherLoaded(forecast);
    }

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
