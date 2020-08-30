package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.Item

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(item: Item)

    @Query("SELECT * FROM Item")
    fun getAll(): LiveData<List<Item>>

    @Query("SELECT * FROM Item WHERE id = :id")
    fun load(id: Int): LiveData<Item>

    @Query("DELETE FROM Item")
    fun deleteAll()
}