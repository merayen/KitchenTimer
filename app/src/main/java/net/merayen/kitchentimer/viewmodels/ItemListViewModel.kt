package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.repositories.ItemRepository

class ItemListViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository = ItemRepository(AppDatabase.getDatabase(application).itemDao())

    fun get() = itemRepository.getAllItems()
}