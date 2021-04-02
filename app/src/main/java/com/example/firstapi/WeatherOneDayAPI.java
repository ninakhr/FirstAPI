package com.example.firstapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherOneDayAPI {
    @GET("/data/2.5/weather")
    Call<Example> getWeatherByCityName(@Query("q") String city,
                                       @Query("appid") String appID);
}