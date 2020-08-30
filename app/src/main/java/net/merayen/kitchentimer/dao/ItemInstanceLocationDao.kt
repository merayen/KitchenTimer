package net.merayen.kitchentimer.dao

import androidx.room.Dao
import androidx.room.Insert
import net.merayen.kitchentimer.data.ItemInstanceLocation

@Dao
interface ItemInstanceLocationDao {
    @Insert
    fun save(itemInstanceLocation: ItemInstanceLocation)
}