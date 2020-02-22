package com.example.weatherapp.FiveDayForecastScreen.mvp;

import com.example.weatherapp.model.FiveDayForecast;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.model.Weather;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;
import com.example.weatherapp.utils.Applog;

import static com.example.weatherapp.utils.Constants.ID_KEY;

public class FiveDayForecastPresenter implements FiveDayForecastContract.Presenter {
    public static String TAG = FiveDayForecastPresenter.class.getSimpleName();

    private FiveDayForecastContract.View view;
    private WeatherRepository weatherRepository;

    public FiveDayForecastPresenter(FiveDayForecastContract.View view, WeatherRepository weatherRepository) {
        this.view = view;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void getFiveDayForecast() {
        weatherRepository.getFiveDayForecast(new WeatherDataProvider.NetworkResponse<FiveDayForecast>() {
            @Override
            public void onLoading() {
                view.weatherLoading();
            }

            @Override
            public void onSuccess(FiveDayForecast object) {
                view.weatherLoaded();
            }

            @Override
            public void onError(String error) {
                view.weatherError(error);
            }
        });
    }

    @Override
    public void onBindRowView(FiveDayForecastRowView row, Forecast forecast) {
        // icon
        if (forecast != null) {
            // checks to see if getWeather() is null or empty
            final Weather weather = forecast.getWeather() != null && forecast.getWeather().size() > 0 ? forecast.getWeather().get(0) : null;
            row.setImage(setIconUrl(weather != null && weather.getIcon() != null ? weather.getIcon() : "1n"));
            row.setDescription(weather != null && weather.getDescription() != null ? weather.getDescription() : "");
            row.setDate(forecast.getDate() == null ? "" : forecast.getDate());
            row.setTemperature((forecast.getMain() == null) ? "N/A" : String.format("%s℉", forecast.getMain().getTemp()));
        }
    }

    @Override
    public String setIconUrl(String icon) {
        final String url = "http://openweathermap.org/img/wn/{{" + ID_KEY + "}}@2x.png";
        return url.replace("{{" + ID_KEY + "}}", icon);
    }

    @Override
    public void onViewCreated() {
        view.toggleErrorMessageVisibility(true);
        view.setRecyclerView();
        getFiveDayForecast();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onCreate() {

    }
}
