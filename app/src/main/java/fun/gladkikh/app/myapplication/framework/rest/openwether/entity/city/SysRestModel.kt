package `fun`.gladkikh.app.myapplication.framework.rest.openwether.entity.city

import com.google.gson.annotations.SerializedName

data class SysRestModel (
    @SerializedName("type")
    var type: Int = 0,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("message")
    var message: Float = 0.toFloat(),
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("sunrise")
    var sunrise: Long = 0,
    @SerializedName("sunset")
    var sunset: Long = 0
)
