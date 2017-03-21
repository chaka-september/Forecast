package com.chakaseptember.forecast.data;

import android.util.Log;

import com.chakaseptember.forecast.BuildConfig;
import com.chakaseptember.forecast.data.request.WeatherApi;
import com.chakaseptember.forecast.data.response.ForecastResponse;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chaka on 21/03/2017.
 */

public class DataManager {
    private static final String TAG = "DataManager";

    public static final String UNITS = "metric";

    public DataManager(){

    }

    public void getForecast(double lat, double lon, final Callback<ForecastResponse> callback){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        Call<ForecastResponse> call = weatherApi.getForecast(lat,lon, BuildConfig.WEATHER_API_KEY, UNITS);

        call.enqueue(new retrofit2.Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: ", t);
                callback.onFailure(t);
            }
        });
    }


}
