package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.repositories.RunningTimerRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class QuickTimersTabViewModel(application: Application) : AndroidViewModel(application) {
    var seconds = 60
    private val runningTaskRepository = RunningTimerRepository(AppDatabase.getDatabase(application).runningTaskDao())
    private val taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())

    fun createQuickTimer() {
        val runningTask = RunningTimer(0, null, "Quick timer", seconds = seconds)
        runningTaskRepository.save(runningTask)
    }
}