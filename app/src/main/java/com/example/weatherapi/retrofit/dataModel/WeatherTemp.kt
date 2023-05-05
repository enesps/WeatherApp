package com.example.weatherapp.retrofit.dataModel

import com.google.gson.annotations.SerializedName

 class WeatherTemp(


    @SerializedName("main")
    var main: Main?=null,
    @SerializedName("id")
    var id:String?=null,
     @SerializedName("name")
     var cityName:String?=null




)


