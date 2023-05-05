package com.example.weatherapp.retrofit.api

import com.example.weatherapp.retrofit.dataModel.WeatherTemp
import com.example.weatherapp.retrofit.dataModel.WeatherTempCityList
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?")
    fun getData(
        @Query("appid") key: String?,
        @Query("q") name: String?
    ): retrofit2.Call<WeatherTemp>

}
interface WeatherApiCity{

    @GET("group?")
    fun getDataCity(
        @Query("appid") key: String?,
        @Query("id") name: String?
    ): retrofit2.Call<WeatherTempCityList>
}