package com.example.weatherapp.repository;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.helpers.LocationHelper;
import com.example.weatherapp.model.FiveDayForecast;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.utils.Applog;
import com.google.gson.Gson;

import static com.example.weatherapp.utils.Constants.BASE_URL;
import static com.example.weatherapp.utils.Constants.WEATHER_API_KEY;

/**
 * WeatherDataProvider is in charge of making the API calls to retrieve the weather and
 * communicate back to our Repository
 */
public class WeatherDataProvider {
    public static String TAG = WeatherDataProvider.class.getSimpleName();

    private Context context;
    private RequestQueue queue;
    private final Gson gson = new Gson();

    public WeatherDataProvider(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    /**
     * this method will get the current weather of the user
     *
     * @param lat             - latitude of the user
     * @param lon             - longitude of the user
     * @param networkResponse - our listener to communicate to the UI
     */
    public void getCurrentWeather(final double lat, final double lon, final NetworkResponse<Forecast> networkResponse) {
        final String url = BASE_URL + "weather?lat=" + lat + "&lon=" + lon + "&units=imperial&appid=" + WEATHER_API_KEY;


        Applog.d(TAG, url);

        // initially loading to communicate to the user with a spinner/snackbar
        networkResponse.onLoading();

        // Request a string response from the provided URL.
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // right now we have the json request we need to convert to our Forecast object
                    final Forecast forecast = gson.fromJson(response.toString(), Forecast.class);
                    Applog.i(TAG, gson.toJson(forecast));

                    // we call onSuccess here to let our UI know everything succeeded
                    networkResponse.onSuccess(forecast);
                },
                error -> {
                    Applog.e(TAG, error.toString());
                    // we call onSuccess here to let our UI know that something went wrong
                    networkResponse.onError(error.getMessage());
                });

        queue.add(request);
    }

    public void getFiveDayForecast(final NetworkResponse<FiveDayForecast> networkResponse) {
        final String url = BASE_URL + "forecast?lat=" + LocationHelper.lat + "&lon=" + LocationHelper.lon + "&units=imperial&appid=" + WEATHER_API_KEY;

        networkResponse.onLoading();

        // Request a string response from the provided URL.
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    // right now we have the json request we need to convert to our Forecast object
                    final FiveDayForecast fiveDayForecast = gson.fromJson(response.toString(), FiveDayForecast.class);
                    Applog.i(TAG, gson.toJson(fiveDayForecast));

                    WeatherRepository.setFiveDayForecast(fiveDayForecast);

                    // we call onSuccess here to let our UI know everything succeeded
                    networkResponse.onSuccess(fiveDayForecast);
                },
                error -> {
                    Applog.e(TAG, error.toString());

                    // we call onSuccess here to let our UI know that something went wrong
                    networkResponse.onError(error.getMessage());
                });

        queue.add(request);
    }

    public interface NetworkResponse<T> {
        void onLoading();

        void onSuccess(T object);

        void onError(String error);
    }
}