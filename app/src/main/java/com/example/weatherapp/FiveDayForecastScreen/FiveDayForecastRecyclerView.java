package com.example.weatherapp.FiveDayForecastScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.weatherapp.FiveDayForecastScreen.mvp.FiveDayForecastContract;
import com.example.weatherapp.FiveDayForecastScreen.mvp.FiveDayForecastRowView;
import com.example.weatherapp.R;
import com.example.weatherapp.helpers.VolleyHelper;
import com.example.weatherapp.model.FiveDayForecast;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherRepository;

import java.util.ArrayList;

/**
 * this recyclerView is used to show the list of forecasts for the 5 day/ 3 hour forecast
 */
public class FiveDayForecastRecyclerView extends RecyclerView.Adapter<FiveDayForecastRecyclerView.FiveDayForecastViewHolder> {
    private final FiveDayForecastContract.Presenter presenter;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public FiveDayForecastRecyclerView(Context context, FiveDayForecastContract.Presenter presenter) {
        this.presenter = presenter;
        requestQueue = VolleyHelper.getInstance(context).getRequestQueue();
        imageLoader = VolleyHelper.getInstance(context).getImageLoader();
    }

    @NonNull
    @Override
    public FiveDayForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FiveDayForecastViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_row, parent, false), imageLoader, requestQueue);
    }

    @Override
    public void onBindViewHolder(@NonNull FiveDayForecastViewHolder holder, int position) {
        presenter.onBindRowView(holder, WeatherRepository.getFiveDayForecast().getForecastList().get(position));
    }

    @Override
    public int getItemCount() {
        final FiveDayForecast fiveDayForecast = WeatherRepository.getFiveDayForecast();

        if (fiveDayForecast != null) {
            final ArrayList<Forecast> forecasts = fiveDayForecast.getForecastList();
            if (forecasts != null && !forecasts.isEmpty()) {
                return forecasts.size();
            }
        }

        return 0;
    }

    public static class FiveDayForecastViewHolder extends RecyclerView.ViewHolder implements FiveDayForecastRowView {
        TextView descriptionTV;
        TextView dateTV;
        TextView temperatureTV;
        NetworkImageView weatherImg;

        ImageLoader imageLoader;
        RequestQueue requestQueue;

        public FiveDayForecastViewHolder(@NonNull View itemView, ImageLoader imageLoader, RequestQueue requestQueue) {
            super(itemView);

            this.imageLoader = imageLoader;
            this.requestQueue = requestQueue;

            descriptionTV = itemView.findViewById(R.id.description_tv);
            dateTV = itemView.findViewById(R.id.date_tv);
            temperatureTV = itemView.findViewById(R.id.temperature_tv);
            weatherImg = itemView.findViewById(R.id.weather_img);
        }

        @Override
        public void setDescription(String description) {
            descriptionTV.setText(description);
        }

        @Override
        public void setDate(String date) {
            dateTV.setText(date);
        }

        @Override
        public void setImage(String url) {
            weatherImg.setImageUrl(url, imageLoader);
        }

        @Override
        public void setTemperature(String temperature) {
            temperatureTV.setText(temperature);
        }
    }
}
