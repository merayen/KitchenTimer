package net.merayen.kitchentimer.dao

import androidx.room.Dao
import androidx.room.Insert
import net.merayen.kitchentimer.data.ItemInstanceProperty

@Dao
interface ItemInstancePropertyDao {
    @Insert
    fun save(itemInstanceProperty: ItemInstanceProperty)
}