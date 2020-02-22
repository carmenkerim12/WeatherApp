package com.example.weatherapp.FiveDayForecastScreen;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.FiveDayForecastScreen.mvp.FiveDayForecastContract;
import com.example.weatherapp.FiveDayForecastScreen.mvp.FiveDayForecastPresenter;
import com.example.weatherapp.R;
import com.example.weatherapp.repository.WeatherDataProvider;
import com.example.weatherapp.repository.WeatherRepository;

/**
 * this fragment is used to display the users 5 day/ 3 hour forecast data
 * <p>
 * this fragment contains the recyclerView needed to display the list of forecasts
 */
public class FiveDayForecastFragment extends Fragment implements FiveDayForecastContract.View {

    public FiveDayForecastFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private TextView errorMsgTV;

    private FiveDayForecastContract.Presenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_five_day_forecast, container, false);

        recyclerView = view.findViewById(R.id.five_day_recyclerview);
        progressBar = view.findViewById(R.id.progress_bar);
        errorMsgTV = view.findViewById(R.id.error_tv);

        setPresenter(new FiveDayForecastPresenter(this, new WeatherRepository(new WeatherDataProvider(getActivity()))));
        presenter.onViewCreated();

        return view;
    }

    @Override
    public void weatherLoading() {
        toggleProgressBar(false);
        toggleRecyclerViewVisibility(true);
    }

    @Override
    public void weatherLoaded() {
        notifyAdapter();
        toggleProgressBar(true);
        toggleRecyclerViewVisibility(false);
    }

    @Override
    public void weatherError(String error) {
        if (getActivity() != null) {
            Toast.makeText(getActivity().getApplicationContext(), "There seems to be something wrong", Toast.LENGTH_SHORT).show();
        }

        toggleErrorMessageVisibility(false);
        toggleProgressBar(true);
    }

    @Override
    public void setRecyclerView() {
        if (getActivity() != null) {
            layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            adapter = new FiveDayForecastRecyclerView(getActivity(), presenter);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public void setErrorMsg(String error) {
        errorMsgTV.setText(error);
    }

    @Override
    public void toggleProgressBar(boolean isHidden) {
        progressBar.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }

    @Override
    public void toggleRecyclerViewVisibility(boolean isHidden) {
        recyclerView.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }

    @Override
    public void toggleErrorMessageVisibility(boolean isHidden) {
        errorMsgTV.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }

    @Override
    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(FiveDayForecastContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
