package `fun`.gladkikh.app.myapplication.framework.db


import `fun`.gladkikh.app.myapplication.framework.db.intity.City
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [City::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}