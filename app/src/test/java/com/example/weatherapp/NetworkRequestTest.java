package com.example.weatherapp;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.example.weatherapp.repository.WeatherRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class NetworkRequestTest {
    public static String TAG = NetworkRequestTest.class.getSimpleName();

    @Mock
    Context context;

    @Mock
    NetworkResponse networkResponse;

    WeatherRepository weatherRepository;

    @Before
    public void init(){

    }

    @Test
    public void fetchCurrentWeather() {

    }
}