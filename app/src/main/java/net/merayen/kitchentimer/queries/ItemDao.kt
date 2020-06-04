package net.merayen.kitchentimer.queries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.data.Task

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(item: Item)

    @Query("SELECT * FROM Item")
    fun loadAll(): List<Task>

    @Query("SELECT * FROM Item WHERE id = :id")
    fun load(id: Int): LiveData<Item>
}