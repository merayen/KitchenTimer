package net.merayen.kitchentimer.fragments.tabs.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Location
import net.merayen.kitchentimer.repositories.StorageRepository

class StorageEditViewModel(application: Application) : AndroidViewModel(application) {
    private val storageRepository = StorageRepository(AppDatabase.getDatabase(application).locationDao())

    fun get(id: Int) = storageRepository.get(id)

    fun insert(location: Location) = storageRepository.insert(location)
}