package com.example.weatherapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {
    /**
     * checks to see if we are connected through the internet
     *
     * @param context - needed to access the system service
     * @return - true if connected | false not connected
     */
    public static boolean isConnectedToNetwork(Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
