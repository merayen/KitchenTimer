package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.data.RunningTask
import net.merayen.kitchentimer.dao.RunningTaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RunningTaskRepository @Inject constructor(
    private val runningTaskDao: RunningTaskDao
) {
    fun save(runningTask: RunningTask) = runningTaskDao.save(runningTask)
    fun get(id: Int) = runningTaskDao.get(id)
    fun getRunningTaskData() = runningTaskDao.getRunningTaskData()
    fun getRunningTaskData(id: Int) = runningTaskDao.getRunningTaskData(id)
    fun get() = runningTaskDao.get()
}