package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.ItemInstance

@Dao
interface ItemInstanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(itemInstance: ItemInstance)

    @Query("SELECT * FROM ItemInstance WHERE item = :itemId")
    fun getByItem(itemId: Int): LiveData<List<ItemInstance>>

    @Query("SELECT * FROM ItemInstance WHERE id = :id")
    fun get(id: Int): LiveData<ItemInstance>
}