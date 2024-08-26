package com.mohdgauri.talentbobweather.ApiClient;


import com.mohdgauri.talentbobweather.DataModels.PlaceWeather.ResponsePlaceWeather;

//import io.reactivex.rxjava3.core.Observable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api_Call {
//    @GET("v1/current.json?key=4ec713a8368f4df0b33133054242608&q={lat},{lon}&aqi=no")
//    Observable<ResponsePlaceWeather> GetLocationWeather(
//            @Query("lat") String lat,
//            @Query("lon") String lon
//    );
//    @GET(getLocationWeather)
//    Observable<ResponsePlaceWeather> GetLocationWeather();


    @GET("v1/current.json")
    Single<ResponsePlaceWeather> GetLocationWeather(
            @Query("key") String apiKey,
            @Query("q") String location,
            @Query("aqi") String aqi
    );

    @GET("v1/current.json")
    Single<ResponsePlaceWeather> GetPlaceWeather(
            @Query("key") String apiKey,
            @Query("q") String location,
            @Query("aqi") String aqi
    );

//    @GET("v1/current.json")
//    Observable<ResponsePlaceWeather> GetPlaceWeather(
//            @Query("key") String key,
//            @Query("place") String place,
//            @Query("aqi") String aqi
//    );
//    @GET(getPlaceWeather)
//    Observable<ResponsePlaceWeather> GetPlaceWeather();
}
