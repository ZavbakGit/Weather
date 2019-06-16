package `fun`.gladkikh.app.myapplication

import `fun`.gladkikh.app.myapplication.framework.db.AppDatabase
import `fun`.gladkikh.app.myapplication.model.Model
import android.app.Application
import android.arch.persistence.room.Room


class App : Application() {
    companion object {
        lateinit var database: AppDatabase
        lateinit var model: Model
        lateinit var appContext: Application
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this

        database = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase")
            .allowMainThreadQueries()
            .build()
        model = Model()



    }
}