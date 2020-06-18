package net.merayen.kitchentimer.viewmodels

import androidx.lifecycle.ViewModel
import net.merayen.kitchentimer.repositories.ItemRepository
import javax.inject.Inject

class ItemEditViewModel : ViewModel() {
    @Inject lateinit var itemRepository: ItemRepository

    fun load(id: Int) = itemRepository.getItem(id)
}