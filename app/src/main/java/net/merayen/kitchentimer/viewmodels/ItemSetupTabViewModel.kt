package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.ItemInstance
import net.merayen.kitchentimer.repositories.ItemRepository

class ItemSetupTabViewModel(application: Application) : AndroidViewModel(application) {
    private val itemRepository = ItemRepository(
        AppDatabase.getDatabase(application).itemDao(),
        AppDatabase.getDatabase(application).itemInstanceDao(),
        AppDatabase.getDatabase(application).itemInstancePropertyDao(),
        AppDatabase.getDatabase(application).itemInstanceLocationDao()
    )

    private var currentItemInstancesLiveData: LiveData<List<ItemInstance>>? = null
    private var currentItemInstancesLiveDataObserver: Observer<List<ItemInstance>>? = null

    fun getItems() = itemRepository.getAllItems()

    // Not sure if this is the correct way to do it, need to read
    fun getItemInstances(lifecycleOwner: LifecycleOwner, itemId: Int, observer: Observer<List<ItemInstance>>) {
        currentItemInstancesLiveData?.removeObserver(currentItemInstancesLiveDataObserver!!)
        currentItemInstancesLiveData = itemRepository.getItemInstancesByItem(itemId).apply {
            observe(lifecycleOwner, observer)
        }
        currentItemInstancesLiveDataObserver = observer
    }
}
