package `fun`.gladkikh.app.myapplication.repository


import `fun`.gladkikh.app.myapplication.App
import `fun`.gladkikh.app.myapplication.framework.db.intity.City
import `fun`.gladkikh.app.myapplication.framework.preference.PreferencesHolder
import `fun`.gladkikh.app.myapplication.framework.rest.openwether.request.OpenWeatherRequestHandler
import `fun`.gladkikh.app.myapplication.model.intity.CityInfoWeather
import io.reactivex.Single

class Repository {

    private val openWeatherRequestHandler = OpenWeatherRequestHandler.getInstance()

    private val preferencesHolder = PreferencesHolder(App.appContext)

    fun getSingleCityInfoWeather(city: String): Single<CityInfoWeather> {
        return openWeatherRequestHandler.getCityInfoWeather(city)
            .map {
                CityInfoWeather(
                    city = it.city,
                    date = it.date,
                    temperature = it.temperature,
                    icon = it.icon
                )
            }
    }

    fun getSingleCityInfoWeatherByGeo(latitude: String,longitude: String): Single<CityInfoWeather> {
        return openWeatherRequestHandler.
            getCityInfoWeatherByGeo(latitude,longitude)
            .map {
                CityInfoWeather(
                    city = it.city,
                    date = it.date,
                    temperature = it.temperature,
                    icon = it.icon
                )
            }
    }

    fun saveCity(name: String) {
        Thread {
            var faund = App.database!!.appDao().getByCity(name)
            if (faund == null) {
                App.database!!.appDao().insert(
                    City(
                        id = null,
                        name = name
                    )
                )
            }
        }.start()
    }

    fun getLiveDataListNameCity() = App.database!!.appDao().getAll()

    fun getCurrentCity() = preferencesHolder.getCurrentCity()
    fun saveCurrentCity(city:String) = preferencesHolder.saveCity(city)

}