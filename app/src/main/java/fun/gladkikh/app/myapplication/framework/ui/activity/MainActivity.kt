package `fun`.gladkikh.app.myapplication.framework.ui.activity

import `fun`.gladkikh.app.myapplication.R
import `fun`.gladkikh.app.myapplication.viewmodel.MainActivityViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        viewModel = ViewModelProviders.of(this)
            .get(MainActivityViewModel::class.java)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //Так легко добавили управление главным меню
        NavigationUI.setupWithNavController(nav_view, navController)
        //Это управление в ActionBar
        //NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)



    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    fun showMessage(message:String){
        Snackbar.make(root, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

}
