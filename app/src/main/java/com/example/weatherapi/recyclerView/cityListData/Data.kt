package com.example.weatherapp.recyclerView.cityListData


import androidx.annotation.DrawableRes
import com.example.weatherapp.recyclerView.cityListModel.CityList

class Data {
    private val cityList = ArrayList<CityList>()
    fun getCityList():ArrayList<CityList> {

        return cityList
        }
    fun addCity(cityName: String,temp: String,@DrawableRes imageResourceId: Int){
        cityList.add(CityList(cityName,temp,imageResourceId))
    }

}