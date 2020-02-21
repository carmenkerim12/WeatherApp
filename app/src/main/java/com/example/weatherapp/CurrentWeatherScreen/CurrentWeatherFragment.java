package com.example.weatherapp.CurrentWeatherScreen;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.weatherapp.CurrentWeatherScreen.presenter.CurrentWeatherPresenter;
import com.example.weatherapp.LocationHelper;
import com.example.weatherapp.R;
import com.example.weatherapp.model.Forecast;
import com.example.weatherapp.repository.WeatherRepository;

public class CurrentWeatherFragment extends Fragment implements CurrentWeatherContract.View {
    private static final String TAG = CurrentWeatherFragment.class.getSimpleName();
    private OnFragmentInteractionListener mListener;

    private TextView cityNameTV;
    private TextView weatherDescriptionTV;
    private TextView tempTV;
    private ProgressBar progressBar;

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
        cityNameTV = view.findViewById(R.id.city_name);
        weatherDescriptionTV = view.findViewById(R.id.description);
        tempTV = view.findViewById(R.id.temp);
        progressBar = view.findViewById(R.id.progress_bar);

        if (getActivity() != null) {
            setPresenter(new CurrentWeatherPresenter(this, new WeatherRepository(getActivity().getApplicationContext()), new LocationHelper(getActivity())));
        }

        currentWeatherPresenter.onViewCreated();

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
        tempTV.setText(text);
    }

    @Override
    public void weatherLoading() {
        progressBar.setVisibility(View.VISIBLE);
        cityNameTV.setVisibility(View.GONE);
        weatherDescriptionTV.setVisibility(View.GONE);
        tempTV.setVisibility(View.GONE);
    }

    @Override
    public void weatherLoaded(Forecast forecast) {
        progressBar.setVisibility(View.GONE);

        cityNameTV.setVisibility(View.VISIBLE);
        weatherDescriptionTV.setVisibility(View.VISIBLE);
        tempTV.setVisibility(View.VISIBLE);

        setCityName(forecast.getName());
        setWeatherDescription(forecast.getWeather().get(0).getDescription());
        setTemp(forecast.getMain().getTemp() + "");
    }

    public void determineContentVisibility() {
        cityNameTV.setVisibility(View.VISIBLE);
        weatherDescriptionTV.setVisibility(View.VISIBLE);
        tempTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void weatherError(String text) {
        if (getActivity() != null)
            Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(CurrentWeatherContract.Presenter presenter) {
        currentWeatherPresenter = presenter;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
