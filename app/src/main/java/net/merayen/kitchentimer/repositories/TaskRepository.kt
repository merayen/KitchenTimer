package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.data.Task
import net.merayen.kitchentimer.dao.TaskDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) {
    fun save(task: Task) = taskDao.save(task)
    fun get(ids: List<Int>) = taskDao.get(ids)
    fun get(id: Int) = taskDao.get(id)
    fun getByDependency(parent: Int) = taskDao.getByDependency(parent)
}