package com.example.weatherapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.weatherapp.CurrentWeatherScreen.CurrentWeatherScreen;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationHelper {
    public static String TAG = LocationHelper.class.getSimpleName();
    public static final int LOCATION_REQUEST_CODE = 1000;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private FragmentActivity activity;

    public static double lon;
    public static double lat;

    public LocationHelper(FragmentActivity activity) {
        this.activity = activity;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    /**
     * this method will get the users last known location if the permissions have been set. Otherwise,
     * we will requestPermission
     */
    void getUsersLastLocation() {
        // check permission
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionForLocation();
        } else {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    setUsersLocation(location.getLongitude(), location.getLatitude());

                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new CurrentWeatherScreen()).commit();

                    Log.i(TAG, "onSuccess: " + location.getLongitude());
                    Log.i(TAG, "onSuccess: " + location.getLatitude());
                }
            });
        }
    }

    /**
     * used to request users location
     */
    public void requestPermissionForLocation() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
    }

    public static boolean isPermissionsGranted(Activity activity) {
        return ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    public static void setUsersLocation(double longitude, double latitude) {
        lon = longitude;
        lat = latitude;
    }

    public FusedLocationProviderClient getFusedLocationProviderClient() {
        return fusedLocationProviderClient;
    }
}
