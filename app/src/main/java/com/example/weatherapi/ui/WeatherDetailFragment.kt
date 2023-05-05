package com.example.weatherapi.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.retrofit.api.WeatherApi
import com.example.weatherapp.retrofit.apiService.WeatherApiService
import com.example.weatherapp.retrofit.dataModel.WeatherTemp
import com.example.weatherapp.viewModel.CityAddViewModel
import retrofit2.Callback
import retrofit2.Response




class WeatherDetailFragment : Fragment() {


    val cityAddViewModel: CityAddViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar?.title = "City Detail"
        val root=inflater.inflate(com.example.weatherapi.R.layout.fragment_weather_detail, container, false)
        val cityName = root.findViewById<TextView>(com.example.weatherapi.R.id.cityTxt)
        val cityTemp=  root.findViewById<TextView>(com.example.weatherapi.R.id.cityTemp)
        val cityTempMin=root.findViewById<TextView>(com.example.weatherapi.R.id.cityTempMin)
        val apiInterface = WeatherApiService.getClient().create(WeatherApi::class.java)
        val call: retrofit2.Call<WeatherTemp> = apiInterface.getData(cityAddViewModel.key.value,cityAddViewModel.cityName.value)
        call.enqueue(object : Callback<WeatherTemp> {
            override fun onResponse(
                call: retrofit2.Call<WeatherTemp>,
                response: Response<WeatherTemp>
            ) {
                if (response.isSuccessful){
                    cityName.text= response.body()?.cityName.toString()
                    cityTemp.text=(response.body()?.main!!.temp-273).toString()
                    cityTempMin.text=(response.body()!!.main!!.temp_max-273).toString()
                }
                else
                {
                    println("not succes")
                }
            }
            override fun onFailure(call: retrofit2.Call<WeatherTemp>, t: Throwable) {
                Log.d("retrofit", "Error")
            }
        })


        return root
    }
}