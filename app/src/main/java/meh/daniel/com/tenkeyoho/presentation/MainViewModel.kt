package meh.daniel.com.tenkeyoho.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.tenkeyoho.domain.WeatherRepository
import meh.daniel.com.tenkeyoho.domain.model.WeatherOfCity

class MainViewModel(private val repository: WeatherRepository) :  ViewModel() {

    private val _weathers : MutableLiveData<WeatherOfCity> = MutableLiveData()
    val weathers : LiveData<WeatherOfCity> get() = _weathers

    init {
        loadWeathers()
    }

    private fun loadWeathers() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weathers = repository.getWeather()
                _weathers.postValue(weathers)
            } catch (e: Throwable) {
                throw IllegalArgumentException("fun getWeatherData exception:", e)
            }
        }
    }
}

class MainViewModelFactory(private val repository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("MainViewModel class not found")
    }
}