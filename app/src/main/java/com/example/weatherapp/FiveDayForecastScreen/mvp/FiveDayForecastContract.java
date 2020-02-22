package com.example.weatherapp.FiveDayForecastScreen.mvp;

import com.example.weatherapp.BasePresenter;
import com.example.weatherapp.BaseView;

public interface FiveDayForecastContract {
    interface Presenter extends BasePresenter {
        void getFiveDayForecast();

        void onBindRowView(FiveDayForecastRowView row, int position);
    }

    interface View extends BaseView<FiveDayForecastContract.Presenter> {
        void weatherLoading();

        void weatherLoaded();

        void weatherError(String error);

        void setRecyclerView();

        void setErrorMsg(String error);

        void toggleProgressBar(boolean isHidden);

        void toggleRecyclerView(boolean isHidden);

        void toggleErrorMessage(boolean isHidden);

        void notifyAdapter();
    }
}