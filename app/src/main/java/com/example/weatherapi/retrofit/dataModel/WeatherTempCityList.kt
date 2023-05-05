package com.example.weatherapp.retrofit.dataModel

import com.google.gson.annotations.SerializedName

class WeatherTempCityList(
    @SerializedName("cnt")
    val cnt: Int,
    @SerializedName("list")
    val list: List<WeatherTemp>
)