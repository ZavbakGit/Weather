package `fun`.gladkikh.app.myapplication.framework.rest.openwether.entity.city

import com.google.gson.annotations.SerializedName

data class CoordRestModel (
    @SerializedName("lon")
    var lon: Float = 0.toFloat(),
    @SerializedName("lat")
    var lat: Float = 0.toFloat()
)
