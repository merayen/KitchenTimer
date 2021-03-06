package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.repositories.LayoutRepository

class KitchenLayoutTabViewModel(application: Application) : AndroidViewModel(application) {
	val layoutBlockRepository = LayoutRepository(AppDatabase.getDatabase(application).layoutBlockDao())
}