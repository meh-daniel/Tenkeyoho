package meh.daniel.com.tenkeyoho.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.tenkeyoho.databinding.ItemColdweatherBinding
import meh.daniel.com.tenkeyoho.databinding.ItemHotweatherBinding
import meh.daniel.com.tenkeyoho.domain.model.Weather

private const val HEAT_TYPE = 1000
private const val COLD_TYPE = 1100

private const val MAX_COLD_TEMP = 19

class WeatherAdapter : ListAdapter<Weather, RecyclerView.ViewHolder>(weatherDiffUtil){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =  when(viewType){
        HEAT_TYPE -> {
            val binding = ItemHotweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HotViewHolder(binding)
        }
        COLD_TYPE -> {
            val binding = ItemColdweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ColdViewHolder(binding)
        }
        else -> {
            throw IllegalArgumentException("expection onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            HEAT_TYPE -> (holder as HotViewHolder).bind(item = getItem(position))
            COLD_TYPE -> (holder as ColdViewHolder).bind(item = getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).temp > MAX_COLD_TEMP){
            HEAT_TYPE
        } else{
            COLD_TYPE
        }
    }
}

class HotViewHolder(private val binding: ItemHotweatherBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Weather){
        binding.tvTemp.text = item.temp.toString()
        binding.tvTime.text = item.dtTxt
    }
}

class ColdViewHolder(private val binding: ItemColdweatherBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Weather){
        binding.tvTemp.text = item.temp.toString()
        binding.tvTime.text = item.dtTxt
    }
}

private val weatherDiffUtil = object : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(
        oldItem: Weather,
        newItem: Weather
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Weather,
        newItem: Weather
    ): Boolean = oldItem == newItem
}