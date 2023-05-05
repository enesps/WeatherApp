package com.example.weatherapp.recyclerView.cityListAdapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapi.R
import com.example.weatherapp.recyclerView.cityListModel.CityList
import com.example.weatherapp.retrofit.dataModel.WeatherTempCityList
import retrofit2.Callback

class Adapter(private val context: Callback<WeatherTempCityList?>,
              private val dataset: List<CityList>,
              private val onCityClickListener: OnCityClickListener

              ): RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.city_card,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     val item=dataset[position]
     holder.itemCityName.text=item.CityName
     holder.itemTemperature.text=item.Temp
        holder.itemImage.setImageResource(item.imageResourceId)
        holder.itemView.setOnClickListener{
            onCityClickListener.onCityClickListener(position)
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
     inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemCityName: TextView=itemView.findViewById(R.id.city_name)
        var itemTemperature: TextView=itemView.findViewById(R.id.temperature)
         var itemImage:ImageView=itemView.findViewById(R.id.imageView)


     }
    interface OnCityClickListener{
        fun onCityClickListener(position: Int)
    }




}