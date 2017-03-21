package com.chakaseptember.forecast.data.request;

import com.chakaseptember.forecast.data.response.ForecastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Chaka on 21/03/2017.
 */

public interface WeatherApi {

    String BASE_URL = "http://api.openweathermap.org";

    @GET("/data/2.5/forecast")
    Call<ForecastResponse> getForecast(@Query("lat") double lat,
                                       @Query("lon") double lon,
                                       @Query("APPID") String appId,
                                       @Query("units") String units);

}
