package net.merayen.kitchentimer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.merayen.kitchentimer.data.Item

class ItemEditViewModel : ViewModel() {
    class MyLiveData : LiveData<Item>() {
        fun setName(name: String) {
            postValue(Item(123, name))
        }
    }

    private val liveData = MyLiveData()

    private var item = Item(1, "")

    fun getItemData() = liveData

    fun setName(name: String) {
        item.name = name
    }
}