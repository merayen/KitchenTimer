package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.ItemInstanceProperty

@Dao
interface ItemInstancePropertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(itemInstanceProperty: ItemInstanceProperty)

    @Query("SELECT * FROM ItemInstanceProperty WHERE ItemInstance = :id")
    fun getByItemInstance(id: Int): LiveData<List<ItemInstanceProperty>>
}