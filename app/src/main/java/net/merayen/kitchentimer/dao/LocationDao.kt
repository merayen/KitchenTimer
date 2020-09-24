package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.Location

@Dao
interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(location: Location)

    @Query("SELECT * FROM Location")
    fun get(): LiveData<List<Location>>

    @Query("SELECT * FROM Location WHERE id = :id")
    fun get(id: Int): LiveData<Location>
}