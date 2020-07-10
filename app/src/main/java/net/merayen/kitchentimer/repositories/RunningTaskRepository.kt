package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.data.RunningTask
import net.merayen.kitchentimer.queries.RunningTaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RunningTaskRepository @Inject constructor(
    private val runningTaskDao: RunningTaskDao
) {
    fun save(runningTask: RunningTask) = runningTaskDao.save(runningTask)
    fun get(id: Int) = runningTaskDao.get(id)
    fun getWithTasks() = runningTaskDao.getWithTasks()
    fun getRunningTaskData(id: Int) = runningTaskDao.getRunningTaskData(id)
    fun get() = runningTaskDao.get()
}