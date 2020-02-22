package com.example.weatherapp.FiveDayForecastScreen.mvp;

import com.example.weatherapp.model.FiveDayForecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

public class FiveDayForecastPresenter implements FiveDayForecastContract.Presenter {
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
    public void onBindRowView(FiveDayForecastRowView row, int position) {
        final FiveDayForecast fiveDayForecast = WeatherRepository.getFiveDayForecast();
        final String ID_KEY = "id";

        // icon
        final String iconId = fiveDayForecast.getForecastList().get(position).getWeather().get(0).getIcon();
        final String url = "http://openweathermap.org/img/wn/{{" + ID_KEY + "}}@2x.png";

        //image
        row.setImage(url.replace("{{" + ID_KEY + "}}", iconId));

        row.setDate(fiveDayForecast.getForecastList().get(position).getDate());

        row.setDescription(fiveDayForecast.getForecastList().get(position).getWeather().get(0).getDescription());

        row.setTemperature(String.format("%s℉", fiveDayForecast.getForecastList().get(position).getMain().getTemp()));
    }

    @Override
    public void onViewCreated() {
        view.toggleErrorMessage(true);
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
