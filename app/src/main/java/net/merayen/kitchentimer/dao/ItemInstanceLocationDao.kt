package net.merayen.kitchentimer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import net.merayen.kitchentimer.data.ItemInstanceLocation

@Dao
interface ItemInstanceLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(itemInstanceLocation: ItemInstanceLocation)
}