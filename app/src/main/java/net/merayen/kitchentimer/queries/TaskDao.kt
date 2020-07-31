package net.merayen.kitchentimer.queries

import androidx.lifecycle.LiveData
import androidx.room.*
import net.merayen.kitchentimer.data.Task
import net.merayen.kitchentimer.data.TaskDependency

@Dao
interface TaskDao {
	@Query("SELECT * FROM Task WHERE id IN (:ids)")
	fun get(ids: List<Int>): LiveData<List<Task>>

	@Query("SELECT * FROM Task WHERE id = :id")
	fun get(id: Int): LiveData<Task>

	@Query("SELECT * FROM Task WHERE recipe = 1")
	fun getRecipes(): LiveData<List<Task>>

	@Query("SELECT Task.* FROM Task JOIN TaskDependency ON Task.id = TaskDependency.dependsOn WHERE TaskDependency.task = :id")
	fun getByDependency(id: Int): LiveData<Task>

	@Query("INSERT INTO TaskDependency (task, dependsOn) VALUES (:taskId, :dependsOnTaskId)")
	fun saveDependency(taskId: Int, dependsOnTaskId: Int)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun save(task: Task)

	@Query("DELETE FROM Task")
	fun deleteAll()
}