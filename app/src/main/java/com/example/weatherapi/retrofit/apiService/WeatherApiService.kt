package com.example.weatherapp.retrofit.apiService



import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiService {


    lateinit var retrofit:Retrofit

    fun getClient(): Retrofit {
        retrofit= Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit

    }

}
