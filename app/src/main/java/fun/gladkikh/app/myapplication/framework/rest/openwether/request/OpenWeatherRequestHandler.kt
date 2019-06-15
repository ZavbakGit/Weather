package `fun`.gladkikh.app.myapplication.framework.rest.openwether.request

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class OpenWeatherRequestHandler {

    private val api: IOpenWeatherRequest

    fun getCityInfoWeather(city: String) =
        api.loadWeather(
            keyApi = keyApi,
            units = units,
            city = city
        ).map {
            CityInfoWeather(
                city = it.name ?: "",
                temperature = it.main?.temp,
                icon = "${baseUrl}img/w/${it.weather?.get(0)?.icon}.png",
                date = Date()
            )
        }


    init {
        api = createAdapter()
    }

    private fun createAdapter(): IOpenWeatherRequest {
        val adapter = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return adapter.create(IOpenWeatherRequest::class.java)
    }


    companion object {
        private const val baseUrl = "http://api.openweathermap.org/"
        private const val keyApi = "762ee61f52313fbd10a4eb54ae4d4de2"
        private const val units = "metric"

        private var singleton: OpenWeatherRequestHandler = OpenWeatherRequestHandler()

        @Synchronized
        fun getInstance(): OpenWeatherRequestHandler {
            return singleton
        }
    }
}