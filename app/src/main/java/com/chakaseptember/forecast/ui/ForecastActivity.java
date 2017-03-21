package com.chakaseptember.forecast.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chakaseptember.forecast.R;
import com.chakaseptember.forecast.data.Callback;
import com.chakaseptember.forecast.data.DataManager;
import com.chakaseptember.forecast.data.response.ForecastResponse;
import com.chakaseptember.forecast.ui.adapters.ForecastAdapter;

public class ForecastActivity extends AppCompatActivity {
    private static final String TAG = "ForecastActivity";

    private static final int REQUEST_ACCESS_FINE_LOCATION = 123;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ForecastAdapter forecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        setupUI();
    }

    private void setupUI() {
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_forecast_swipe_refresh_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getForecastForCurrentLocation();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.activity_forecast_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        forecastAdapter = new ForecastAdapter(this, null);
        recyclerView.setAdapter(forecastAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            getLocationPermission();
        }else{
            getForecastForCurrentLocation();
        }
    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                showSnackBar(getString(R.string.error_location_permission_required));
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_ACCESS_FINE_LOCATION);
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_ACCESS_FINE_LOCATION);
            }
        }
    }

    private void getForecastForCurrentLocation() {
        Log.d(TAG, "getForecastForCurrentLocation() called");
        Location lastKnownLocation = getLastKnownLocation();
        if(lastKnownLocation!=null) {
            getForecastForLocation(lastKnownLocation);
        }else {
            swipeRefreshLayout.setRefreshing(false);
            showSnackBar(getString(R.string.error_location_unavailable));
            Log.d(TAG, "getForecastForCurrentLocation: location was null");
        }
    }

    private void getForecastForLocation(Location lastKnownLocation) {
        Log.d(TAG, "getForecastForLocation() called with: lastKnownLocation = [" + lastKnownLocation + "]");
        swipeRefreshLayout.setRefreshing(true);
        DataManager dataManager = new DataManager();
        
        dataManager.getForecast(lastKnownLocation.getLatitude(),
                lastKnownLocation.getLongitude(),
                new Callback<ForecastResponse>() {
                    @Override
                    public void onSuccess(ForecastResponse forecastResponse) {
                        showForecast(forecastResponse);

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        showForecastError(throwable);
                    }
                });
        
    }

    private void showForecastError(Throwable throwable) {
        swipeRefreshLayout.setRefreshing(false);
        showSnackBar(throwable.getMessage());
    }

    private void showForecast(ForecastResponse forecastResponse) {
        swipeRefreshLayout.setRefreshing(false);
        forecastAdapter.setForecastResponse(forecastResponse);
        getSupportActionBar().setTitle(getString(R.string.forecast)+ " - " + forecastResponse.getCity().getName());
    }

    private Location getLastKnownLocation() {
        Location lastKnownLocation = null;
        try {
            LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                Log.d(TAG, "getLastKnownLocation: no provider enabled");
                showSnackBar(getString(R.string.error_location_unavailable));
            } else {
                if (isGPSEnabled) {
                    lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                }else if(isNetworkEnabled) {
                   lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                }
                
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            showSnackBar(getString(R.string.error_location_permission_required));
            getLocationPermission();
        }

        return lastKnownLocation;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getForecastForCurrentLocation();

                } else {
                    showSnackBar(getString(R.string.error_location_permission_required));
                }
                return;
            }
        }
    }

    private void showSnackBar(String message){
        Snackbar.make(swipeRefreshLayout,message,Snackbar.LENGTH_LONG).show();
    }
}
