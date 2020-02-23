package com.example.weatherapp.helpers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.weatherapp.MainActivity;

import java.util.List;

/**
 * this class is used to help provide with
 * <p>
 * - finding the users location
 * - requesting permission for location
 * - storing and setting the users coords
 */
public class LocationHelper {
    public static String TAG = LocationHelper.class.getSimpleName();
    public static final int LOCATION_REQUEST_CODE = 1000;
    private static final long INTERVAL_UPDATE = 5000;
    public static double lon;
    public static double lat;

    private MainActivity activity;
    public LocationManager locationManager;
    String provider;
    LocationResponse locationResponse;

    public LocationHelper(FragmentActivity activity) {
        this.activity = ((MainActivity) activity);
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), true);
    }

    public void getUsersLastLocation(LocationResponse locationResponse, LocationListener locationListener) {
        this.locationResponse = locationResponse;

        // check permission
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionForLocation();
        } else {
            // request location updates
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, INTERVAL_UPDATE, INTERVAL_UPDATE, locationListener);

            final List<String> providers = locationManager.getProviders(true);
            for (String provider : providers) {
                final Location location = locationManager.getLastKnownLocation(provider);
                if (location == null) {
                    continue;
                }

                setUsersLocation(location.getLongitude(), location.getLatitude());
                locationResponse.onSuccess();
                return;
            }

            locationResponse.onError();
        }
    }

    /**
     * this method will get the users last known location if the permissions have been set. Otherwise,
     * we will requestPermission
     */
//    public void getUsersLastLocation(OnSuccessListener<Location> onSuccessListener, OnFailureListener onFailureListener) {
//        // check permission
//        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissionForLocation();
//        } else {
//            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(onSuccessListener).addOnFailureListener(onFailureListener);
//        }
//    }

    /**
     * used to request users location
     */
    public void requestPermissionForLocation() {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_CODE);
    }

    public static void setUsersLocation(double longitude, double latitude) {
        lon = longitude;
        lat = latitude;
    }

    public interface LocationResponse {
        void onSuccess();

        void onError();
    }

    public interface LocationHelperInterface {
        LocationHelper getLocationHelper();
    }
}
