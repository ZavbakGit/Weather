package `fun`.gladkikh.app.myapplication.framework.rest.openwether.request

import java.util.*

data class CityInfoWeather(val city:String,
                           val temperature:Float?,
                           val icon:String?,
                           val date: Date?)