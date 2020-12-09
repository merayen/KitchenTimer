package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.repositories.RunningTimerRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class WorkbenchTabViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())
    private val runningTaskRepository = RunningTimerRepository(AppDatabase.getDatabase(application).runningTaskDao())

    /**
     * The list of all the active tasks on the left side of the workspace
     */
    val runningTasks = runningTaskRepository.getRunningTimerData()

    fun get(id: Int) = runningTaskRepository.getRunningTimerData(id)
}
