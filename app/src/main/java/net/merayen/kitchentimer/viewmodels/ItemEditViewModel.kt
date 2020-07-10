package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.repositories.ItemRepository

class ItemEditViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository = ItemRepository(AppDatabase.getDatabase(application).itemDao())
    private var currentId: Int? = null

    fun save(item: Item) = viewModelScope.launch(Dispatchers.IO) {
        itemRepository.save(item)
    }

    fun get(id: Int): LiveData<Item> {
        currentId = id
        return itemRepository.getItem(id)
    }

    fun get() = itemRepository.getItem(currentId ?: throw RuntimeException("No id given yet")).value
}