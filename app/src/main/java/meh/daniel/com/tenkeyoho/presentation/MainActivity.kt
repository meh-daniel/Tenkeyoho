package meh.daniel.com.tenkeyoho.presentation

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import meh.daniel.com.tenkeyoho.App
import meh.daniel.com.tenkeyoho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val weatherAdapter = WeatherAdapter(::sendSomething, ::sendSomething)

    private val requestMultiplePermissions =     registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach {
            Log.e("DEBUG", "${it.key} = ${it.value}")
        }
    }

    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory(App.weatherRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        observeViewModel()
        binding.button.setOnClickListener {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    private fun sendSomething(temp: String, dtTxt: String) {
        val share = Intent.createChooser(Intent().apply {
            type = "text/html"
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_SUBJECT, "Информация о погоде города: ${mainViewModel.nameCityOfTheCurrentWeathers}")
            putExtra(Intent.EXTRA_TEXT, "$dtTxt -  $temp")
        }, null)
        startActivity(share)
    }

    private fun observeViewModel() {
        mainViewModel.weathers.observe(this) {
            weatherAdapter.submitList(it.weathers)
        }
    }

    private fun initRecyclerView() {
        binding.rvWeather.adapter = weatherAdapter
        binding.rvWeather.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}