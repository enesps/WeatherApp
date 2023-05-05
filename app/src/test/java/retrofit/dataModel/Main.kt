package com.example.weatherapp.retrofit.dataModel

import com.google.gson.annotations.SerializedName

 class Main(
    @SerializedName("feels_like")
    val feels_like: Float =0f,
    @SerializedName("humidity")
    val humidity: Int=0,
    @SerializedName("pressure")
    val pressure: Int=0,
    @SerializedName("temp")
    val temp: Float =0f,
    @SerializedName("temp_max")
    val temp_max: Float =0f,
    @SerializedName("temp_min")
    val temp_min: Float =0f
)