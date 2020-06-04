package net.merayen.kitchentimer.repositories

import android.content.Context
import net.merayen.kitchentimer.AppDatabase

class ItemRepository {
    fun getAllItems(context: Context) {
        AppDatabase.getDatabase(context)
    }
}