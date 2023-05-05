package com.example.weatherapp.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class CityAddViewModel: ViewModel() {


    val cityeditText = MutableLiveData<String>()
    private val _idList = MutableLiveData<String>()
    val idList: LiveData<String> = _idList


    private val _key = MutableLiveData<String>()
    val key: LiveData<String> = _key
    private val _cityName = MutableLiveData<String>()
    var cityName: LiveData<String> = _cityName



    fun setKey(key: String) {
        this._key.value = key
    }
    fun setCityName(cityName: String) {
        this._cityName.value = cityName
    }
    fun setIdList(idList: String) {
        this._idList.value = idList
    }
    fun cityTextEmpty():Boolean{
        return cityeditText.value.isNullOrEmpty()
    }
}