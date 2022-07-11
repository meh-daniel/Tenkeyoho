package meh.daniel.com.tenkeyoho.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.tenkeyoho.data.model.WeathersNW
import meh.daniel.com.tenkeyoho.databinding.ItemColdweatherBinding
import meh.daniel.com.tenkeyoho.databinding.ItemHeatweatherBinding

private const val HEAT_TYPE = 1000
private const val COLD_TYPE = 1100

class WeatherAdapter : ListAdapter<WeathersNW.WeatherInfo, RecyclerView.ViewHolder>(weatherDiffUtil){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =  when(viewType){
        HEAT_TYPE -> {
            val binding = ItemHeatweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HeatViewHolder(binding)
        }
        COLD_TYPE -> {
            val binding = ItemColdweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ColdViewHolder(binding)
        }
        else -> {
            throw IllegalArgumentException("fuck onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            HEAT_TYPE -> (holder as HeatViewHolder).bind(item = getItem(position))
            COLD_TYPE -> (holder as ColdViewHolder).bind(item = getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).main.temp > 15){
            HEAT_TYPE
        } else{
            COLD_TYPE
        }
    }
}

class HeatViewHolder(private val binding: ItemHeatweatherBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: WeathersNW.WeatherInfo){
        binding.tvTemp.text = item.main.temp.toString()
    }
}

class ColdViewHolder(private val binding: ItemColdweatherBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: WeathersNW.WeatherInfo){
        binding.tvTemp.text = item.main.temp.toString()
    }
}

private val weatherDiffUtil = object : DiffUtil.ItemCallback<WeathersNW.WeatherInfo>() {
    override fun areItemsTheSame(
        oldItem: WeathersNW.WeatherInfo,
        newItem: WeathersNW.WeatherInfo
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: WeathersNW.WeatherInfo,
        newItem: WeathersNW.WeatherInfo
    ): Boolean = oldItem == newItem

}