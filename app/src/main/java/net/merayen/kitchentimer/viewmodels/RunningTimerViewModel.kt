package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.livedata.RunningTimerData
import net.merayen.kitchentimer.repositories.RunningTimerRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class RunningTimerViewModel(application: Application) : AndroidViewModel(application) {
    private var runningTaskRepository = RunningTimerRepository(AppDatabase.getDatabase(application).runningTaskDao())
    private var taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())

    fun getRunningTimers() = runningTaskRepository.getRunningTimerData()
    fun getRunningTimer(id: Int) = runningTaskRepository.getRunningTimerData(id)

    fun save(runningTimerData: RunningTimerData) {
        runningTaskRepository.save(RunningTimer(
            id = runningTimerData.runningTimerId,
            name = runningTimerData.runningTimerName,
            start = runningTimerData.runningTimerStart,
            elapsed = runningTimerData.runningTimerElapsed,
            seconds = runningTimerData.runningTimerSeconds,
            task = null,
        ))
    }

    val tasks = runningTaskRepository.get()
}
