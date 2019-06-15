package `fun`.gladkikh.app.myapplication

import `fun`.gladkikh.app.myapplication.framework.db.AppDatabase
import `fun`.gladkikh.app.myapplication.model.Model
import android.app.Application
import android.arch.persistence.room.Room


class App : Application() {
    companion object {
        var database: AppDatabase? = null
        var model:Model? = null
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "mydatabase")
            .allowMainThreadQueries()
            .build()

        model = Model()

    }
}