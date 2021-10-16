package com.example.weatherapp.repo

import com.example.weatherapp.networking.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

   suspend fun getCurrentWeather(key : String ,query : String,days : Int) = weatherService.getCurrentTemp(key,query,days)

}