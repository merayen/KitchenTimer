package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Task
import net.merayen.kitchentimer.repositories.TaskRepository

class WorkbenchTabViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())

    /**
     * Task that is currently being shown on the right side of the workspace
     */
    var task: LiveData<Task>? = null
        private set

    /**
     * The list of all the active tasks on the left side of the workspace
     */
    val taskList: Unit? = null

    fun use(id: Int) {
        task = taskRepository.get(id)
    }
}
