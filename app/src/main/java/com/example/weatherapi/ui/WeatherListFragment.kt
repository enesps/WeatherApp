package com.example.weatherapi.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapi.R
import com.example.weatherapi.databinding.FragmentWeatherListBinding
import com.example.weatherapp.recyclerView.cityListAdapter.Adapter
import com.example.weatherapp.recyclerView.cityListData.Data
import com.example.weatherapp.retrofit.api.WeatherApiCity
import com.example.weatherapp.retrofit.apiService.WeatherApiService
import com.example.weatherapp.retrofit.dataModel.WeatherTempCityList
import com.example.weatherapp.viewModel.CityAddViewModel
import retrofit2.*
import java.text.DecimalFormat





class WeatherListFragment : Fragment(), Adapter.OnCityClickListener {

    val cityAddViewModel: CityAddViewModel by activityViewModels()
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView:RecyclerView
    lateinit var cityAdapter: Adapter
    val cityListData=Data()

    private var binding: FragmentWeatherListBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentWeatherListBinding.inflate(inflater, container, false)

        binding = fragmentBinding
        return fragmentBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Weather List"
        binding?.apply {
            // Specify the fragment as the lifecycle owner
            lifecycleOwner = viewLifecycleOwner
            // Assign the fragment
            weatherListViewModel=cityAddViewModel

            weatherListFragment = this@WeatherListFragment
        }
    }

    override fun onStart() {
        super.onStart()
        cityAddViewModel.setKey("50fc26ae10465cc679ba9ded06a837fd")
        getWeatherData()
    }
    override fun onDestroyView() {

        super.onDestroyView()
        binding = null
    }
    fun goToCityAddScreen(){

        findNavController().navigate(R.id.action_weatherListFragment_to_cityAddFragment)
    }
    fun goToWeatherDetailScreen(){
        findNavController().navigate(R.id.action_weatherListFragment_to_weatherDetailFragment)
    }


    fun getWeatherData() {
        val sharedPref : SharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val cities: String? = sharedPref.getString("cityList4","2542007")
        println(cities)
        cityListData.getCityList().clear()


            val apiInterface = WeatherApiService.getClient().create(WeatherApiCity::class.java)
            val call = apiInterface.getDataCity(cityAddViewModel.key.value,cities)
            call.enqueue(object : Callback<WeatherTempCityList?> {
                override fun onResponse(
                    call: Call<WeatherTempCityList?>,
                    response: Response<WeatherTempCityList?>
                ) {
                    if (response.isSuccessful){
                        for(city in response.body()!!.list){
                            DecimalFormat("##C").format(city.main!!.temp)
                           cityListData.addCity(city.cityName.toString(),( DecimalFormat("##C").format(city.main!!.temp-273)).toString(),R.drawable.few_clouds)

                        }
                         linearLayoutManager=LinearLayoutManager(context)
                         recyclerView = view?.findViewById(R.id.recycler_view)!!
                        recyclerView.layoutManager = linearLayoutManager
                        cityAdapter= Adapter(this,cityListData.getCityList(),this@WeatherListFragment)
                        recyclerView.adapter = cityAdapter

                    }
                    else
                    {
                        Log.d("retrofit", "Not Successful")
                    }
                }
                override fun onFailure(call: Call<WeatherTempCityList?>, t: Throwable) {
                    Log.d("retrofit", "Error")
                }
            })


    }

    override fun onCityClickListener(position: Int) {
        cityAddViewModel.setCityName(cityListData.getCityList()[position].CityName)
        goToWeatherDetailScreen()
    }
}


