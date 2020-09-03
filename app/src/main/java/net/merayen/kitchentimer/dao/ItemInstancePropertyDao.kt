package net.merayen.kitchentimer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import net.merayen.kitchentimer.data.ItemInstanceProperty

@Dao
interface ItemInstancePropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(itemInstanceProperty: ItemInstanceProperty)
}