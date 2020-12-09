package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.dao.RunningTimerDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RunningTimerRepository @Inject constructor(
    private val runningTimerDao: RunningTimerDao
) {
    fun save(runningTimer: RunningTimer) =
        AppDatabase.getInstance().queryExecutor.execute { runningTimerDao.save(runningTimer) }

    fun get(id: Int) = runningTimerDao.get(id)
    fun getRunningTimerData() = runningTimerDao.getRunningTimerData()
    fun getRunningTimerData(id: Int) = runningTimerDao.getRunningTimerData(id)
    fun get() = runningTimerDao.get()
}