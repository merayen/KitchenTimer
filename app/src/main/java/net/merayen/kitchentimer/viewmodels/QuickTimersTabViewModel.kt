package net.merayen.kitchentimer.viewmodels

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.RunningTask
import net.merayen.kitchentimer.repositories.RunningTaskRepository
import net.merayen.kitchentimer.repositories.TaskRepository
import java.util.concurrent.Executor

class QuickTimersTabViewModel(application: Application) : AndroidViewModel(application) {
    var seconds = 60
    private val runningTaskRepository = RunningTaskRepository(AppDatabase.getDatabase(application).runningTaskDao())
    private val taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())

    fun createQuickTimer() {
        val runningTask = RunningTask(0, null, "Quick timer", seconds = seconds)
        runningTaskRepository.save(runningTask)
    }
}