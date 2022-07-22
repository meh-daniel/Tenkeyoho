package meh.daniel.com.tenkeyoho.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import meh.daniel.com.tenkeyoho.App
import meh.daniel.com.tenkeyoho.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    private val mainViewModel : MainViewModel by viewModels {
        MainViewModelFactory(App.weatherRepository)
    }

    private val weatherAdapter = WeatherAdapter(::sendInfoAboutCurrentWeatherInFormatEmail, ::sendInfoAboutCurrentWeatherInFormatEmail)

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var currentLongitude: Double? = null
    private var currentLatitude: Double? = null

    private val requestMultiplePermissions = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        permissions.entries.forEach {
            Log.e("DEBUG", "${it.key} = ${it.value}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        initGeoLocation()
        observeViewModel()
        requestLocationPermission()
        checkPermissionGiven()
    }

    private fun checkPermissionGiven() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            initGeoLocation()
        } else requestLocationPermission()
    }

    private fun initGeoLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.btnGetCurrentLocation.setOnClickListener {
            checkPermissionGiven()
            getLocation()
            if (currentLatitude != null) mainViewModel.loadWeathers(
                currentLat = currentLatitude!!,
                currentLon = currentLongitude!!
            )
        }
    }

    private fun requestLocationPermission() {
        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            Snackbar.make(binding.root.rootView, "Need permission your location for app", Snackbar.LENGTH_INDEFINITE)
                .setAction("OK"){
                    launchMultiPermission()
                }
                .show()
        } else {
            launchMultiPermission()
        }
    }

    private fun launchMultiPermission(){
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener { location : Location? ->
            location?.let {
                currentLongitude = it.longitude
                currentLatitude = it.latitude
            }
        }
    }

    private fun sendInfoAboutCurrentWeatherInFormatEmail(temp: String, dtTxt: String) {
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