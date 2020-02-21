package com.example.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.CurrentWeatherScreen.CurrentWeatherFragment;
import com.example.weatherapp.utils.Applog;

import static com.example.weatherapp.LocationHelper.LOCATION_REQUEST_CODE;

public class MainActivity extends AppCompatActivity implements CurrentWeatherFragment.OnFragmentInteractionListener {
    // location helper used to help us with getting the users location
    LocationHelper locationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationHelper = new LocationHelper(this);

        // get users location
        locationHelper.getUsersLastLocation();

//        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CurrentWeatherFragment()).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // If request is cancelled, the result arrays are empty.
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // request permission and exit out of method
                        locationHelper.requestPermissionForLocation();
                        return;
                    }
                }

                // when user grants permission, we will call on get last location
                locationHelper.getFusedLocationProviderClient().getLastLocation().addOnSuccessListener(this, location -> {
                    // it is known for location to be null at times. Safe to check
                    if (location != null) {
                        LocationHelper.setUsersLocation(location.getLongitude(), location.getLatitude());

                        Applog.i("CARMEN: lon", location.getLongitude() + "");
                        Applog.i("CARMEN: lat", location.getLatitude() + "");
                    }
                });
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
