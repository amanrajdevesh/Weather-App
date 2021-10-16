package com.example.weatherapp.viewModels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.modals.WeatherClass
import com.example.weatherapp.repo.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val weather : MutableLiveData<WeatherClass> = MutableLiveData()

    fun getCurrTemp(key : String ,query : String,days : Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getCurrentWeather(key,query,days)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        weather.value = response.body()
                    } else {
                        Log.d("Aman", "Error fetching data")
                    }
                }
        }
    }
}