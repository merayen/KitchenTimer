package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.repositories.RunningTimerRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class RunningTimerViewModel(application: Application) : AndroidViewModel(application) {
    private var runningTaskRepository = RunningTimerRepository(AppDatabase.getDatabase(application).runningTaskDao())
    private var taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())

    fun getRunningTimers() = runningTaskRepository.getRunningTimers()
    fun getWithTasks() = runningTaskRepository.getWithTasks()
    fun getRunningTimer(id: Int) = runningTaskRepository.getRunningTimer(id)
    fun save(runningTimer: RunningTimer) = runningTaskRepository.save(runningTimer)
    val tasks = runningTaskRepository.get()
}
