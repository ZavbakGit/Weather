package `fun`.gladkikh.app.myapplication.framework.db.intity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class City(
    @PrimaryKey(autoGenerate = true) var id: Long?,
    var name:String
)