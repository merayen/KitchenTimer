package net.merayen.kitchentimer.repositories

import androidx.lifecycle.LiveData
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.queries.ItemDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
)
{
    fun getAllItems() {
        TODO()
    }

    fun getItem(id: Int): LiveData<Item> = itemDao.load(id)
}