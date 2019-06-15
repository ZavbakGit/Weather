package `fun`.gladkikh.app.myapplication.ui.fragment


import `fun`.gladkikh.app.myapplication.R
import `fun`.gladkikh.app.myapplication.viewmodel.WeatherViewModel
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : BaseFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val model = ViewModelProviders.of(this)
            .get(WeatherViewModel::class.java)

        model.message.observe(viewLifecycleOwner, Observer { mess ->
            mess?.let {
                tvMessage.text = mess
            }

        })
    }

    override fun getLayout(): Int = R.layout.weather_fragment

}