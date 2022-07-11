package meh.daniel.com.tenkeyoho.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.tenkeyoho.App
import meh.daniel.com.tenkeyoho.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private var weatherAdapter = WeatherAdapter()

    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory(repository = App.weatherRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        mainViewModel.weathers.observe(this) {
            weatherAdapter.submitList(it)
        }
    }

    private fun initRecyclerView() {
        binding.rvWeather.adapter = weatherAdapter
        binding.rvWeather.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}