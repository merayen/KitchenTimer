package net.merayen.kitchentimer.queries

import androidx.room.*
import net.merayen.kitchentimer.data.Task

@Dao
interface TaskDao {
	@Query("SELECT * FROM Task WHERE id IN (:ids)")
	fun loadByIds(ids: IntArray): List<Task>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun save(task: Task)

	@Query("DELETE FROM Task")
	fun deleteAll()
}