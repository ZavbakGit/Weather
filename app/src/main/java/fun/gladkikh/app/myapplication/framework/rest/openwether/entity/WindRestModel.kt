package `fun`.gladkikh.app.myapplication.framework.rest.openwether.entity

import com.google.gson.annotations.SerializedName

data class WindRestModel (
    @SerializedName("speed")
    var speed: Float = 0.toFloat(),
    @SerializedName("deg")
    var deg: Float = 0.toFloat()
)
