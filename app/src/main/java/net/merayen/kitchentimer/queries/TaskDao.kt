package net.merayen.kitchentimer.queries

import androidx.room.*
import net.merayen.kitchentimer.data.Task

@Dao
interface TaskDao {
	@Query("SELECT * FROM Task WHERE id IN (:ids)")
	fun loadByIds(ids: IntArray): List<Task>

	//@Insert
	//suspend fun insert(task: Task)

	//@Delete
	//suspend fun delete(task: Task)
}