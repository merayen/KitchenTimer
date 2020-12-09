package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.repositories.RunningTimerRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class RunningTimerViewModel(application: Application) : AndroidViewModel(application) {
    private var runningTaskRepository = RunningTimerRepository(AppDatabase.getDatabase(application).runningTaskDao())
    private var taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())

    fun getRunningTimers() = runningTaskRepository.getRunningTimerData()

    val tasks = runningTaskRepository.get()
}
