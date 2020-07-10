package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Task
import net.merayen.kitchentimer.livedata.RunningTaskData
import net.merayen.kitchentimer.repositories.RunningTaskRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class WorkbenchTabViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())
    private val runningTaskRepository = RunningTaskRepository(AppDatabase.getDatabase(application).runningTaskDao())

    /**
     * The list of all the active tasks on the left side of the workspace
     */
    val runningTasks: List<RunningTaskData>? = null

    fun get(id: Int) = runningTaskRepository.getRunningTaskData(id)
}
