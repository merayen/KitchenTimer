package net.merayen.kitchentimer.fragments.tabs.storage

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Location
import net.merayen.kitchentimer.repositories.StorageRepository

class StorageTabViewModel(application: Application) : AndroidViewModel(application) {
    private val storageRepository = StorageRepository(AppDatabase.getDatabase(application).locationDao())

    fun get(): LiveData<List<Location>> = storageRepository.get()
}
