package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.repositories.LayoutRepository

class LayoutViewModel(application: Application) : AndroidViewModel(application) {
	val layoutRepository = LayoutRepository(AppDatabase.getDatabase(application).layoutBlockDao())
}