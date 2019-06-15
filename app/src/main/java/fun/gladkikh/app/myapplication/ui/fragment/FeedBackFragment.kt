package `fun`.gladkikh.app.myapplication.ui.fragment


import `fun`.gladkikh.app.myapplication.R
import `fun`.gladkikh.app.myapplication.viewmodel.FeedBackViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle


class FeedBackFragment : BaseFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val model = ViewModelProviders.of(this)
            .get(FeedBackViewModel::class.java)
    }

    override fun getLayout(): Int = R.layout.about_fragment
}