package `fun`.gladkikh.app.myapplication.framework.rest.openwether.entity

import com.google.gson.annotations.SerializedName

data class MainRestModel (
    @SerializedName("temp")
    var temp: Float = 0.toFloat(),
    @SerializedName("pressure")
    var pressure: Int = 0,
    @SerializedName("humidity")
    var humidity: Int = 0,
    @SerializedName("temp_min")
    var tempMin: Float = 0.toFloat(),
    @SerializedName("temp_max")
    var tempMax: Float = 0.toFloat()
)
