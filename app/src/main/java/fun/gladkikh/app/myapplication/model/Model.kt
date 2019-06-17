package `fun`.gladkikh.app.myapplication.model

import `fun`.gladkikh.app.myapplication.repository.Repository

class Model{
    private val repository = Repository()
    fun getWeatherCity(city:String) = repository.getSingleCityInfoWeather(city)
    fun getLiveDataListNameCity() = repository.getLiveDataListNameCity()
    fun saveCity(name:String) = repository.saveCity(name)

    fun getCurrentCity() = repository.getCurrentCity()
    fun saveCurrentCity(city:String) = repository.saveCurrentCity(city)

}