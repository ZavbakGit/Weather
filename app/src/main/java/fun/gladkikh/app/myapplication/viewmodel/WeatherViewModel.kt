package `fun`.gladkikh.app.myapplication.viewmodel


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.util.*

class WeatherViewModel : ViewModel() {
    val message = MutableLiveData<String>()

    init {
        Thread {
            Thread.sleep(3000)
            message.postValue(Date().toString())
        }.start()
    }
}