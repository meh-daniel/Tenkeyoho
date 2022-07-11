package meh.daniel.com.tenkeyoho.presentation

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import meh.daniel.com.tenkeyoho.data.model.WeathersNW
import meh.daniel.com.tenkeyoho.domain.WeatherRepository

class MainViewModel(private val repository: WeatherRepository) :  ViewModel() {

    private val _weathers : MutableLiveData<List<WeathersNW.WeatherInfo>> = MutableLiveData()
    val weathers : LiveData<List<WeathersNW.WeatherInfo>> get() = _weathers

    init {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val weathers = repository.getWeather()
                _weathers.postValue(weathers.list)
            } catch (e : Throwable){
                Log.e("xxx", "gfsadfas", e)
            }
        }
    }
}

class MainViewModelFactory(private val repository: WeatherRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}