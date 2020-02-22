package com.example.weatherapp;

import com.example.weatherapp.FiveDayForecastScreen.mvp.FiveDayForecastContract;
import com.example.weatherapp.FiveDayForecastScreen.mvp.FiveDayForecastPresenter;
import com.example.weatherapp.FiveDayForecastScreen.mvp.FiveDayForecastRowView;
import com.example.weatherapp.model.FiveDayForecast;
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

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * testing class for our FiveDayForecast presenter
 */
@RunWith(MockitoJUnitRunner.class)
public class FiveDayForecastPresenterTest {
    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private FiveDayForecastContract.View view;

    @Mock
    private FiveDayForecastPresenter presenter;

    @Mock
    private FiveDayForecastRowView forecastRowView;

    // used to capture our networkResponse listener
    @Captor
    private ArgumentCaptor<WeatherDataProvider.NetworkResponse<FiveDayForecast>> networkResponseArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new FiveDayForecastPresenter(view, weatherRepository);
    }

    /**
     * useful to check when we fetch the five day forecast that during a successful use case.
     * Our presenter should correspond correctly calling the correct views
     */
    @Test
    public void getFiveDayForecast_WeatherLoaded_Success() {
        presenter.getFiveDayForecast();
        // when we make a call to get the five day forecast, we first want to call loading on the listener
        verify(weatherRepository).getFiveDayForecast(networkResponseArgumentCaptor.capture());
        networkResponseArgumentCaptor.getValue().onLoading();
        // lets make sure that our view is also in a loading state
        verify(view).weatherLoading();
        // onSuccess is called once we get the response back from the api
        networkResponseArgumentCaptor.getValue().onSuccess(new FiveDayForecast());
        // make sure out view is loaded once we get that callback
        verify(view).weatherLoaded();
    }

    /**
     * same use case as weatherLoaded_success. However, we want to
     * make sure when a error happens that our view is corresponding properly
     */
    @Test
    public void getFiveDayForecast_WeatherLoaded_Error() {
        presenter.getFiveDayForecast();
        verify(weatherRepository).getFiveDayForecast(networkResponseArgumentCaptor.capture());
        networkResponseArgumentCaptor.getValue().onLoading();
        verify(view).weatherLoading();
        networkResponseArgumentCaptor.getValue().onError("Error");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(view).weatherError(argumentCaptor.capture());
        assertEquals("Error", argumentCaptor.getValue());
    }

    @Test
    public void onViewCreated() {
        presenter.onViewCreated();
        verify(view).toggleErrorMessageVisibility(true);
        verify(view).setRecyclerView();
    }

    /**
     * whenever we fetch the forecast, we want to make sure we are handling any missing values
     */
    @Test
    public void onBindView_emptyValues_should_stillPopulateDefaultValues() {
        Forecast fiveDayForecast = Mockito.mock(Forecast.class);
        presenter.onBindRowView(forecastRowView, fiveDayForecast);

        verify(forecastRowView).setImage("http://openweathermap.org/img/wn/1n@2x.png");
        verify(forecastRowView).setDate("");
        verify(forecastRowView).setDescription("");
        verify(forecastRowView).setTemperature("N/A");
    }
}
