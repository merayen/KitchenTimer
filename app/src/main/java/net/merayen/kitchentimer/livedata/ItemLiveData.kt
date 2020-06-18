package net.merayen.kitchentimer.livedata

import androidx.lifecycle.MutableLiveData
import net.merayen.kitchentimer.data.Item

class ItemLiveData(val id: Int) : MutableLiveData<Item>() {
    override fun onActive() {
        println("Ble aktiv")
    }

    override fun onInactive() {
        println("Ble inaktiv")
    }
}