package `fun`.gladkikh.app.myapplication.framework.rest.openwether.entity

import com.google.gson.annotations.SerializedName

data class WeatherRequestRestModel (
    @SerializedName("coord")
    var coordinates: CoordRestModel? = null,
    @SerializedName("weather")
    var weather: List<WeatherRestModel>? = null,
    @SerializedName("base")
    var base: String? = null,
    @SerializedName("main")
    var main: MainRestModel? = null,
    @SerializedName("visibility")
    var visibility: Int = 0,
    @SerializedName("wind")
    var wind: WindRestModel? = null,
    @SerializedName("clouds")
    var clouds: CloudsRestModel? = null,
    @SerializedName("dt")
    var dt: Long = 0,
    @SerializedName("sys")
    var sys: SysRestModel? = null,
    @SerializedName("id")
    var id: Long = 0,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("cod")
    var cod: Int = 0
)
