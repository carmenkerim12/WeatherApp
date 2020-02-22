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
import com.example.weatherapp.FiveDayForecastScreen.FiveDayForecastFragment;
import com.example.weatherapp.R;
import com.example.weatherapp.helpers.LocationHelper;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

/**
 * this fragment is used to
 * <p>
 * - get the users location
 * - get the users current weather by their location
 * - a way to navigate to view their five day/ 3 hour forecast
 */
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
        final View view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        cityNameTV = view.findViewById(R.id.city_name_tv);
        weatherDescriptionTV = view.findViewById(R.id.description_tv);
        tempTV = view.findViewById(R.id.temperature_tv);
        progressBar = view.findViewById(R.id.progress_bar);
        buttonFiveDayForecastBtn = view.findViewById(R.id.five_day_btn);

        if (getActivity() != null) {
            setPresenter(new CurrentWeatherPresenter(this,
                    new WeatherRepository(new WeatherDataProvider(getActivity().getApplicationContext())),
                    new LocationHelper(getActivity())));

            // onViewCreated will take care of getting the users location and weather
            currentWeatherPresenter.onViewCreated();
        }

        buttonFiveDayForecastBtn.setOnClickListener(v -> onNavigateToFiveDayForecast());

        return view;
    }

    @Override
    public void weatherLoading() {
        toggleContentVisibility(true);
    }

    @Override
    public void weatherLoaded(Forecast forecast) {
        toggleContentVisibility(false);
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
        tempTV.setText(String.format("%s℉", text));
    }

    @Override
    public void toggleContentVisibility(boolean isWeatherLoading) {
        if (isWeatherLoading) {
            progressBar.setVisibility(View.VISIBLE);
            cityNameTV.setVisibility(View.GONE);
            weatherDescriptionTV.setVisibility(View.GONE);
            tempTV.setVisibility(View.GONE);
            buttonFiveDayForecastBtn.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            cityNameTV.setVisibility(View.VISIBLE);
            weatherDescriptionTV.setVisibility(View.VISIBLE);
            tempTV.setVisibility(View.VISIBLE);
            buttonFiveDayForecastBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onNavigateToFiveDayForecast() {
        // navigating to the five day forecast screen
        if (getActivity() != null) {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, new FiveDayForecastFragment())
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    @Override
    public void setPresenter(CurrentWeatherContract.Presenter presenter) {
        currentWeatherPresenter = presenter;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
