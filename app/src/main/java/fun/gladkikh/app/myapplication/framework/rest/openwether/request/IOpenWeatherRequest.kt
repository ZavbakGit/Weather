package `fun`.gladkikh.app.myapplication.framework.rest.openwether.request


import `fun`.gladkikh.app.myapplication.framework.rest.openwether.entity.WeatherRequestRestModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IOpenWeatherRequest {
    @GET("data/2.5/weather")
    fun loadWeather(
        @Query("q") city: String,
        @Query("appid") keyApi: String,
        @Query("units") units: String
    ): Single<WeatherRequestRestModel>
}
