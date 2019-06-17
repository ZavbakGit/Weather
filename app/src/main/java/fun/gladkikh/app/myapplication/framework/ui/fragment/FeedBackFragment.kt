package `fun`.gladkikh.app.myapplication.framework.ui.fragment


import `fun`.gladkikh.app.myapplication.R
import `fun`.gladkikh.app.myapplication.framework.ui.activity.MainActivity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.feedback_fragment.*


class FeedBackFragment : BaseFragment() {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btSend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ag@799000.ru"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Message")
            intent.putExtra(Intent.EXTRA_TEXT, edMessage.text)

            startActivity(Intent.createChooser(intent, "Send Email"))
            (activity as MainActivity).navController.navigateUp()
        }
    }

    override fun getLayout(): Int = R.layout.feedback_fragment
}