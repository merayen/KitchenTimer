package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import net.merayen.kitchentimer.data.Task

@Dao
interface TaskDao {
	@Query("SELECT * FROM Task WHERE taskId IN (:ids)")
	fun get(ids: List<Int>): LiveData<List<Task>>

	@Query("SELECT * FROM Task WHERE taskId = :id")
	fun get(id: Int): LiveData<Task>

	@Query("SELECT Task.* FROM Task JOIN TaskDependency ON Task.taskId = TaskDependency.dependsOn WHERE TaskDependency.task = :id")
	fun getByDependency(id: Int): LiveData<Task>

	@Query("INSERT INTO TaskDependency (task, dependsOn) VALUES (:taskId, :dependsOnTaskId)")
	fun saveDependency(taskId: Int, dependsOnTaskId: Int)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun save(task: Task)

	@Query("DELETE FROM Task")
	fun deleteAll()
}