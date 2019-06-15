package `fun`.gladkikh.app.myapplication.viewmodel


import `fun`.gladkikh.app.myapplication.App
import `fun`.gladkikh.app.myapplication.App.Companion.model
import `fun`.gladkikh.app.myapplication.framework.db.intity.City
import `fun`.gladkikh.app.myapplication.utils.SingleLiveEvent
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class WeatherViewModel : ViewModel() {

    var currentCity: String? = null
        set(value) {
            field = value
            loadWeather(value ?: "")
            if (!value.isNullOrBlank()){
                model!!.saveCity(value!!)
            }
        }

    val cityLd = MutableLiveData<String>()
    val temperatureLd = MutableLiveData<String>()
    val iconLd = MutableLiveData<String>()

    val errorMessageLd = SingleLiveEvent<String>()

    val listNameCityLd = model?.getLiveDataListNameCity()

    private var disposableGetWeatherSity: Disposable? = null

    init {
        loadWeather("London")
    }


    private fun loadWeather(city: String) {
        temperatureLd.value = ""
        cityLd.value = ""
        iconLd.value = ""

        model?.let { it ->
            disposableGetWeatherSity = it.getWeatherCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    temperatureLd.value = "${it.temperature.toString()} \u2103"
                    cityLd.value = it.city
                    iconLd.value = it.icon
                }, {
                    errorMessageLd.value = "Ошибка загрузки погоды!"
                })
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposableGetWeatherSity.let {
            disposableGetWeatherSity?.dispose()
        }
    }


}