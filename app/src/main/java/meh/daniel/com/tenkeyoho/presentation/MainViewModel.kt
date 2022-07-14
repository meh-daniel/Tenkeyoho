package meh.daniel.com.tenkeyoho.presentation

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.tenkeyoho.data.model.WeathersNW
import meh.daniel.com.tenkeyoho.domain.network.WeatherRepository

class MainViewModel(private val repository: WeatherRepository) :  ViewModel() {

    private val _weathers : MutableLiveData<List<WeathersNW.WeatherInfo>> = MutableLiveData()
    val weathers : LiveData<List<WeathersNW.WeatherInfo>> get() = _weathers

    init {
        getWeathersData()
    }

    private fun getWeathersData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weathers = repository.getWeather()
                _weathers.postValue(weathers.list)
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