package com.example.weatherapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.weatherapp.CurrentWeatherScreen.CurrentWeatherFragment;

import static com.example.weatherapp.LocationHelper.LOCATION_REQUEST_CODE;

public class MainActivity extends AppCompatActivity implements CurrentWeatherFragment.OnFragmentInteractionListener {
    // location helper used to help us with getting the users location
    LocationHelper locationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationHelper = new LocationHelper(this);

        if (savedInstanceState == null) {
            // we check if we don't have permissions,
            // if we don't then we will request it otherwise lets navigate
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locationHelper.requestPermissionForLocation();
            } else {
                navigateToCurrentWeatherScreen();
            }
        }
    }

    /**
     * used to navigate to our main screen
     */
    public void navigateToCurrentWeatherScreen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new CurrentWeatherFragment()).commit();
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
                navigateToCurrentWeatherScreen();
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
