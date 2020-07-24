package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.repositories.RunningTaskRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class RunningTimerViewModel(application: Application) : AndroidViewModel(application) {
    private var runningTaskRepository = RunningTaskRepository(AppDatabase.getDatabase(application).runningTaskDao())
    private var taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())

    fun getRunningTasks() = runningTaskRepository.getRunningTaskData()

    val tasks = runningTaskRepository.get()
}
