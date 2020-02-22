package com.example.weatherapp.FiveDayForecastScreen.mvp;

import com.example.weatherapp.BasePresenter;
import com.example.weatherapp.BaseView;
import com.example.weatherapp.model.Forecast;

public interface FiveDayForecastContract {
    interface Presenter extends BasePresenter {
        /**
         * calls to the api and handles changing state to error,successful or loading.
         */
        void getFiveDayForecast();

        /**
         * binds to the recyclerView row
         *
         * @param row
         * @param forecast
         */
        void onBindRowView(FiveDayForecastRowView row, Forecast forecast);

        /**
         * will replace {{id}} in api string with the icon id
         *
         * @param url
         */
        String setIconUrl(String url);
    }

    interface View extends BaseView<FiveDayForecastContract.Presenter> {
        void weatherLoading();

        /**
         * whenever we get the 5 day/3 hour forecast, we will call this method
         * to reload the arrayList for the recyclerView
         */
        void weatherLoaded();

        /**
         * whenever there is a error, whether it be no network or api error
         */
        void weatherError(String error);

        // sets up the recyclerView including the layoutManager
        void setRecyclerView();

        /**
         * this will set the error message in the center of the screen
         *
         * @param error - what went wrong
         */
        void setErrorMsg(String error);

        /**
         * our loading bar whenever we make an api call
         *
         * @param isHidden
         */
        void toggleProgressBar(boolean isHidden);

        /**
         * sets the visibility of the recyclerView. Useful for when
         * we are showing a progressBar during api call
         *
         * @param isHidden
         */
        void toggleRecyclerViewVisibility(boolean isHidden);

        /**
         * sets the visibility of the error message. Useful for when nothing loads
         * and we want the user to see something other than a blank screen.
         *
         * @param isHidden
         */
        void toggleErrorMessageVisibility(boolean isHidden);

        /**
         * notifies datasetChange for the recyclerView
         */
        void notifyAdapter();
    }
}