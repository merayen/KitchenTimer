package net.merayen.kitchentimer.repositories

import androidx.lifecycle.LiveData
import androidx.room.Query
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.queries.ItemDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
) {
    fun getAllItems() = itemDao.getAll()

    fun getItem(id: Int): LiveData<Item> = itemDao.load(id)

    fun save(item: Item) = itemDao.save(item)
}