package com.example.weatherapp.CurrentWeather;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherContract;
import com.example.weatherapp.CurrentWeather.mvp.CurrentWeatherPresenter;
import com.example.weatherapp.LocationHelper;
import com.example.weatherapp.R;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

public class CurrentWeatherFragment extends Fragment implements CurrentWeatherContract.View {
    private static final String TAG = CurrentWeatherFragment.class.getSimpleName();

    private TextView cityNameTV;
    private TextView weatherDescriptionTV;
    private TextView tempTV;
    private ProgressBar progressBar;
    private Button buttonFiveDayForecastBtn;

    private CurrentWeatherContract.Presenter currentWeatherPresenter;

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_weather_current, container, false);
        cityNameTV = view.findViewById(R.id.city_name_tv);
        weatherDescriptionTV = view.findViewById(R.id.description_tv);
        tempTV = view.findViewById(R.id.temperature_tv);
        progressBar = view.findViewById(R.id.progress_bar);
        buttonFiveDayForecastBtn = view.findViewById(R.id.five_day_btn);

        if (getActivity() != null) {
            setPresenter(new CurrentWeatherPresenter(this, new WeatherRepository(new WeatherDataProvider(getActivity())), new LocationHelper(getActivity())));
        }

        currentWeatherPresenter.onViewCreated();

        buttonFiveDayForecastBtn.setOnClickListener(v -> {

        });

        return view;
    }

    @Override
    public void setCityName(String text) {
        cityNameTV.setText(text);
    }

    @Override
    public void setWeatherDescription(String text) {
        weatherDescriptionTV.setText(text);
    }

    @Override
    public void setTemp(String text) {
        tempTV.setText(text + "\u2109");
    }

    @Override
    public void weatherLoading() {
        progressBar.setVisibility(View.VISIBLE);
        cityNameTV.setVisibility(View.GONE);
        weatherDescriptionTV.setVisibility(View.GONE);
        tempTV.setVisibility(View.GONE);
        buttonFiveDayForecastBtn.setVisibility(View.GONE);
    }

    @Override
    public void weatherLoaded(Forecast forecast) {
        progressBar.setVisibility(View.GONE);

        cityNameTV.setVisibility(View.VISIBLE);
        weatherDescriptionTV.setVisibility(View.VISIBLE);
        tempTV.setVisibility(View.VISIBLE);
        buttonFiveDayForecastBtn.setVisibility(View.VISIBLE);

        setCityName(forecast.getName());
        setWeatherDescription(forecast.getWeather().get(0).getDescription());
        setTemp(forecast.getMain().getTemp() + "");
    }

    @Override
    public void weatherError(String text) {
        if (getActivity() != null) {
            Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    public void determineContentVisibility() {
        cityNameTV.setVisibility(View.VISIBLE);
        weatherDescriptionTV.setVisibility(View.VISIBLE);
        tempTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(CurrentWeatherContract.Presenter presenter) {
        currentWeatherPresenter = presenter;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
