package net.merayen.kitchentimer.repositories

import androidx.lifecycle.LiveData
import net.merayen.kitchentimer.dao.LocationDao
import net.merayen.kitchentimer.data.Location
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageRepository @Inject constructor(private val locationDao: LocationDao) {
    fun get(): LiveData<List<Location>> = locationDao.get()
    fun get(id: Int): LiveData<Location> = locationDao.get(id)
}