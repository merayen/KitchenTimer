package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.repositories.ItemRepository

class ItemSetupTabViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository = ItemRepository(
        AppDatabase.getDatabase(application).itemDao(),
        AppDatabase.getDatabase(application).itemInstanceDao(),
        AppDatabase.getDatabase(application).itemInstancePropertyDao(),
        AppDatabase.getDatabase(application).itemInstanceLocationDao()
    )

    fun getItems() = itemRepository.getAllItems()
    fun getItemInstances(itemId: Int) = itemRepository.getItemInstancesByItem(itemId)
}
