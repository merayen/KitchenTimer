package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.data.Task
import net.merayen.kitchentimer.queries.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun save(task: Task) = taskDao.save(task)
    fun get(id: Int) = taskDao.get(id)
}