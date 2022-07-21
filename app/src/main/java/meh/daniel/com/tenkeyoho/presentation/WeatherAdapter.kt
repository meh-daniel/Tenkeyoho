package meh.daniel.com.tenkeyoho.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import meh.daniel.com.tenkeyoho.databinding.ItemColdweatherBinding
import meh.daniel.com.tenkeyoho.databinding.ItemHotweatherBinding
import meh.daniel.com.tenkeyoho.domain.model.Weather

private const val HOT_TYPE = 1000
private const val COLD_TYPE = 1100

private const val MAX_COLD_TEMP = 19

class WeatherAdapter(
    private val onColdViewClick: (temp: String, dtTxt: String) -> Unit,
    private val onHotViewClick: (temp: String, dtTxt: String) -> Unit
) : ListAdapter<Weather, RecyclerView.ViewHolder>(weatherDiffUtil){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =  when(viewType){
        HOT_TYPE -> {
            val binding = ItemHotweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            HotViewHolder(binding, onHotViewClick)
        }
        COLD_TYPE -> {
            val binding = ItemColdweatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ColdViewHolder(binding, onColdViewClick)
        }
        else -> {
            throw IllegalArgumentException("expection onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HotViewHolder -> holder.bind(item = getItem(position))
            is ColdViewHolder -> holder.bind(item = getItem(position))
            else -> throw Exception("onBindViewHolder unknown view type exception")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).temp > MAX_COLD_TEMP){
            HOT_TYPE
        } else{
            COLD_TYPE
        }
    }
}

class HotViewHolder(private val binding: ItemHotweatherBinding, private val onHotViewClick: (temp: String, dtTxt: String) -> Unit) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Weather){
        binding.tvTemp.text = item.temp.toString()
        binding.tvTime.text = item.dtTxt
        binding.hotItemHot.setOnLongClickListener {
            onHotViewClick(item.temp.toString(), item.dtTxt)
            true
        }
    }
}

class ColdViewHolder(private val binding: ItemColdweatherBinding, private val onColdViewClick: (temp: String, dtTxt: String) -> Unit) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Weather){
        binding.tvTemp.text = item.temp.toString()
        binding.tvTime.text = item.dtTxt
        binding.coldItemView.setOnLongClickListener {
            onColdViewClick(item.temp.toString(), item.dtTxt)
            true
        }
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