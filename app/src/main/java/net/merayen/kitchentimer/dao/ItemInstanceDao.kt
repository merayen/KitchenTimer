package net.merayen.kitchentimer.dao

import androidx.room.Dao
import androidx.room.Insert
import net.merayen.kitchentimer.data.ItemInstance

@Dao
interface ItemInstanceDao {
    @Insert
    fun save(itemInstance: ItemInstance)
}