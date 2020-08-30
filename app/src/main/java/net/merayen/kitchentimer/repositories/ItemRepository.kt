package net.merayen.kitchentimer.repositories

import androidx.lifecycle.LiveData
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.dao.ItemDao
import net.merayen.kitchentimer.dao.ItemInstanceDao
import net.merayen.kitchentimer.dao.ItemInstanceLocationDao
import net.merayen.kitchentimer.dao.ItemInstancePropertyDao
import net.merayen.kitchentimer.data.ItemInstance
import net.merayen.kitchentimer.data.ItemInstanceLocation
import net.merayen.kitchentimer.data.ItemInstanceProperty
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemRepository @Inject constructor(
    private val itemDao: ItemDao,
    private val itemInstanceDao: ItemInstanceDao,
    private val itemInstancePropertyDao: ItemInstancePropertyDao,
    private val itemInstanceLocationDao: ItemInstanceLocationDao
) {
    fun getAllItems() = itemDao.getAll()

    fun getItem(id: Int): LiveData<Item> = itemDao.load(id)

    fun save(item: Item) = itemDao.save(item)
    fun save(itemInstance: ItemInstance) = itemInstanceDao.save(itemInstance)
    fun save(itemInstanceProperty: ItemInstanceProperty) = itemInstancePropertyDao.save(itemInstanceProperty)
    fun save(itemInstanceLocation: ItemInstanceLocation) = itemInstanceLocationDao.save(itemInstanceLocation)
}