package `fun`.gladkikh.app.myapplication.framework.ui.fragment


import `fun`.gladkikh.app.myapplication.App
import `fun`.gladkikh.app.myapplication.R
import `fun`.gladkikh.app.myapplication.framework.ui.activity.MainActivity
import `fun`.gladkikh.app.myapplication.viewmodel.WeatherViewModel
import android.Manifest
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_fragment.*
import java.util.*


class WeatherFragment : BaseFragment() {

    private lateinit var locationManager: LocationManager
    private lateinit var viewModel: WeatherViewModel


    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location?) {
            locationManager.removeUpdates(this)

            location?.let {
                val geocoder = Geocoder(activity, Locale.getDefault())
                val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                //val addresses = geocoder.getFromLocation(55.80650092, 37.59041132, 1)

                if (!addresses.isEmpty()) {
                    viewModel.changeCity(addresses[0].locality)
                } else {
                    (activity as MainActivity).showMessage("Не смогли получить текущее положение!")
                }
            }
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

        }

        override fun onProviderEnabled(p0: String?) {

        }

        override fun onProviderDisabled(p0: String?) {

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this)
            .get(WeatherViewModel::class.java)

        locationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager


        btAddCity.setOnClickListener {
            viewModel.changeCity(actvCity.text.toString())
            actvCity.text.clear()
        }

        btGeo.setOnClickListener {
            viewModel.onClickGetWetherGeo()
        }

        actvCity.setOnItemClickListener { _, _, _, _ ->
            viewModel.changeCity(actvCity.text.toString())
            actvCity.text.clear()
        }

        actvCity.setOnClickListener {
            actvCity.text = actvCity.text.append("")
            actvCity.setSelection(actvCity.text.length)
        }


        viewModel.temperatureLd.observe(viewLifecycleOwner, Observer { temperature ->
            tvInfo.text = temperature ?: ""
        })

        viewModel.cityLd.observe(viewLifecycleOwner, Observer { city ->
            tvCity.text = city ?: ""
        })

        viewModel.iconLd.observe(viewLifecycleOwner, Observer { icon ->
            loadImage(icon)
        })

        viewModel.listNameCityLd.observe(viewLifecycleOwner, Observer { list ->
            if (list != null) {
                setAdapterAutoComplete(list.map {
                    it.name
                })
            } else {
                setAdapterAutoComplete(listOf())
            }
        })

        viewModel.errorMessageLd.observe(viewLifecycleOwner, Observer { errorMessage ->
            errorMessage?.let {
                (activity as MainActivity).showMessage(errorMessage)
            }
        })

        viewModel.loadWeatherByGeo.observe(viewLifecycleOwner, Observer { loadWeather ->
            if (!loadWeather!!) {
                return@Observer
            }

            if (!checkPermission()) {
                val permissions = arrayOf(
                    Manifest.permission.RECEIVE_SMS, Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.RECEIVE_SMS, Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity!!.requestPermissions(permissions, 79955)
                }
            } else {
                val criteria = Criteria()
                criteria.accuracy = Criteria.ACCURACY_LOW
                val provider = locationManager.getBestProvider(criteria, true)

                locationManager.requestLocationUpdates(
                    provider,
                    1000 * 10, 100f,
                    locationListener
                )
            }

        })
    }

    private fun setAdapterAutoComplete(list: List<String>) {
        activity?.let {
            val adapter = ArrayAdapter<String>(
                activity as Context,
                R.layout.support_simple_spinner_dropdown_item,
                list
            )
            actvCity.setAdapter<ArrayAdapter<String>>(adapter)
        }

    }

    private fun loadImage(path: String?) {
        try {
            if (path.isNullOrBlank()) {
                ivIcon.setImageDrawable(null)
            } else {
                Picasso.get()
                    .load(path)
                    .into(ivIcon)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getLayout(): Int = R.layout.weather_fragment

    private fun checkPermission(): Boolean {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        return !(ActivityCompat.checkSelfPermission(
            App.appContext,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
                &&

                ActivityCompat.checkSelfPermission(
                    App.appContext,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                )
    }

    override fun onStop() {
        super.onStop()
        locationManager.removeUpdates(locationListener)
    }

}