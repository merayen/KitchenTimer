package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.dao.LocationDao
import net.merayen.kitchentimer.data.Location
import net.merayen.kitchentimer.repositories.StorageRepository
import net.merayen.kitchentimer.repositories.TaskRepository
import javax.inject.Inject
import javax.inject.Singleton

class StorageTabViewModel(application: Application) : AndroidViewModel(application) {
    private val storageRepository = StorageRepository(AppDatabase.getDatabase(application).locationDao())

    fun get(): LiveData<List<Location>> = storageRepository.get()
}
