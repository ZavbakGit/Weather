package `fun`.gladkikh.app.myapplication.ui.fragment


import `fun`.gladkikh.app.myapplication.R
import `fun`.gladkikh.app.myapplication.viewmodel.AboutViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle


class AboutFragment : BaseFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val model = ViewModelProviders.of(this)
            .get(AboutViewModel::class.java)
    }

    override fun getLayout(): Int = R.layout.about_fragment
}