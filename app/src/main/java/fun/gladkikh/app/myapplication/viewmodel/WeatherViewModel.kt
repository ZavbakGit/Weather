package `fun`.gladkikh.app.myapplication.viewmodel

import `fun`.gladkikh.app.myapplication.App.Companion.model
import `fun`.gladkikh.app.myapplication.utils.SingleLiveEvent
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : ViewModel() {

    val cityLd = MutableLiveData<String>()
    val temperatureLd = MutableLiveData<String>()
    val iconLd = MutableLiveData<String>()
    val listNameCityLd = model.getLiveDataListNameCity()

    val loadWeatherByGeo = SingleLiveEvent<Boolean>()

    val errorMessageLd = SingleLiveEvent<String>()

    private var disposableGetWeatherCity: Disposable? = null

    init {
        loadWeather(model.getCurrentCity())
    }

    /*
    private fun loadWeatherByGeo(
        latitude: String, longitude: String
        , isChange: Boolean = false
    ) {
        temperatureLd.value = ""
        cityLd.value = ""
        iconLd.value = ""

        disposableGetWeatherCity =
            model.getWeatherCityByGeo(
                //"35","139"
                latitude,
                longitude
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    temperatureLd.value = "${it.temperature.toString()} \u2103"
                    cityLd.value = it.city
                    iconLd.value = it.icon
                    if (isChange) {
                        model.saveCity(it.city)
                        model.saveCurrentCity(it.city)
                    }

                }, {
                    errorMessageLd.value = "Ошибка загрузки погоды!"
                })
    }*/

    private fun loadWeather(city: String, isChange: Boolean = false) {
        temperatureLd.value = ""
        cityLd.value = ""
        iconLd.value = ""

        disposableGetWeatherCity = model.getWeatherCity(city)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                temperatureLd.value = "${it.temperature.toString()} \u2103"
                cityLd.value = it.city
                iconLd.value = it.icon
                if (isChange) {
                    model.saveCity(it.city)
                    model.saveCurrentCity(it.city)
                }

            }, {
                errorMessageLd.value = "Ошибка загрузки погоды!"
            })
    }

    fun changeCity(city: String) {
        if (!city.isBlank()) {
            loadWeather(city, true)
        } else {
            errorMessageLd.value = "Пустой город"
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposableGetWeatherCity.let {
            disposableGetWeatherCity?.dispose()
        }
    }

    /*
    //Получение погоды по гео данным
    fun getWetherByGeo(latitude: String, longitude: String) {
        loadWeatherByGeo(latitude, longitude)
    }*/

    fun onClickGetWetherGeo() {
        loadWeatherByGeo.value = true
    }


}