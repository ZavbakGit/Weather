package `fun`.gladkikh.app.myapplication.framework.db


import `fun`.gladkikh.app.myapplication.framework.db.intity.City
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query


@Dao
interface AppDao {
    @Query("SELECT * from City")
    fun getAll(): LiveData<List<City>>

    @Query("SELECT * FROM City WHERE name = :cityName")
    fun getByCity(cityName:String):City?

    @Insert(onConflict = REPLACE)
    fun insert(city: City)
}