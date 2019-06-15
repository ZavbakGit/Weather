package `fun`.gladkikh.app.myapplication.framework.ui.fragment


import `fun`.gladkikh.app.myapplication.R
import `fun`.gladkikh.app.myapplication.framework.ui.activity.MainActivity
import `fun`.gladkikh.app.myapplication.viewmodel.WeatherViewModel
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : BaseFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this)
            .get(WeatherViewModel::class.java)


        btAddCity.setOnClickListener {
            viewModel.currentCity = actvCity.text.toString()
            actvCity.text.clear()
        }

        actvCity.setOnItemClickListener { _, _, _, _ ->
            viewModel.currentCity = actvCity.text.toString()
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

        viewModel.listNameCityLd!!.observe(viewLifecycleOwner, Observer { list ->
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

    }

    fun setAdapterAutoComplete(list: List<String>) {
        activity?.let {
            val adapter = ArrayAdapter<String>(
                activity as Context,
                R.layout.support_simple_spinner_dropdown_item,
                list
            )
            actvCity.setAdapter<ArrayAdapter<String>>(adapter)
        }

    }

    fun loadImage(path: String?) {
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

}