package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Item
import net.merayen.kitchentimer.data.ItemInstance
import net.merayen.kitchentimer.repositories.ItemRepository

class ItemInstanceEditViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository = ItemRepository(
        AppDatabase.getDatabase(application).itemDao(),
        AppDatabase.getDatabase(application).itemInstanceDao(),
        AppDatabase.getDatabase(application).itemInstancePropertyDao(),
        AppDatabase.getDatabase(application).itemInstanceLocationDao()
    )

    fun save(item: ItemInstance) = viewModelScope.launch(Dispatchers.IO) {
        itemRepository.save(item)
    }

    fun get(id: Int): LiveData<ItemInstance> {
        return itemRepository.getItemInstance(id)
    }
}