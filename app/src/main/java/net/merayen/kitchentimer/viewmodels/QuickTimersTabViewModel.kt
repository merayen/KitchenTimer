package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.repositories.RunningTaskRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class QuickTimersTabViewModel(application: Application) : AndroidViewModel(application) {
    private val runningTaskRepository = RunningTaskRepository(AppDatabase.getDatabase(application).runningTaskDao())
    private val taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())
}