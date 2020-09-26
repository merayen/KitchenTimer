package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import net.merayen.kitchentimer.data.Location

@Dao
interface LocationDao {
    @Insert
    fun save(location: Location)

    @Update
    fun update(location: Location)

    @Query("SELECT * FROM Location")
    fun get(): LiveData<List<Location>>

    @Query("SELECT * FROM Location WHERE id = :id")
    fun get(id: Int): LiveData<Location>
}