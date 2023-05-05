package com.example.weatherapi.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.weatherapi.databinding.FragmentCityAddBinding
import com.example.weatherapp.retrofit.api.WeatherApi
import com.example.weatherapp.retrofit.apiService.WeatherApiService
import com.example.weatherapp.retrofit.dataModel.WeatherTemp
import com.example.weatherapp.viewModel.CityAddViewModel
import retrofit2.Callback
import retrofit2.Response


class CityAddFragment : Fragment() {
    private var binding: FragmentCityAddBinding? = null
    val sharedViewModel: CityAddViewModel by activityViewModels()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentCityAddBinding.inflate(inflater, container, false)

        binding = fragmentBinding

        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "City Add"
        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner

            // Assign the view model to a property in the binding class
            cityAddViewModel = sharedViewModel

            // Assign the fragment
            cityAddFragment = this@CityAddFragment
        }
    }


    fun cityAdd(){
        sharedViewModel.setKey("50fc26ae10465cc679ba9ded06a837fd")
        val sharedPref : SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor=sharedPref.edit()
        var flag=true
        val apiInterface = WeatherApiService.getClient().create(WeatherApi::class.java)
        val call: retrofit2.Call<WeatherTemp> = apiInterface.getData(sharedViewModel.key.value,sharedViewModel.cityeditText.value)
        val cities= sharedPref.getString("cityList","2542007")?.split(",")
      if(!sharedViewModel.cityTextEmpty()){

          call.enqueue(object : Callback<WeatherTemp?> {
              override fun onResponse(
                  call: retrofit2.Call<WeatherTemp?>,
                  response: Response<WeatherTemp?>
              ) {
                  //data model
      if(response.isSuccessful){
          for (city in cities!!){
      if(response.body()?.id ==city){
          flag=false
    }
          }

     if(flag){
          sharedViewModel.setIdList(sharedPref.getString("cityList4","2542007")+","+response.body()!!.id.toString())

    //data insert and apply
          editor.putString("cityList4",sharedViewModel.idList.value)
          editor.apply()
    // data print
          Toast.makeText(activity,   "congratulations.", Toast.LENGTH_SHORT).show()

     }
     else{
          Toast.makeText(activity, "same city", Toast.LENGTH_SHORT).show()
     }
     }
     else{
          Toast.makeText(activity, "No such city was found.", Toast.LENGTH_SHORT).show()
     }
      }

              override fun onFailure(call: retrofit2.Call<WeatherTemp?>, t: Throwable) {
                  Toast.makeText(activity, "Retrofit Error.", Toast.LENGTH_SHORT).show()
              }
          })
      }
      else{
          // DATA is null or empty
          Toast.makeText(activity, "city name is null or empty .", Toast.LENGTH_SHORT).show()
      }
    }

}