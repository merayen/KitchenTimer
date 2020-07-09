package net.merayen.kitchentimer.queries

import androidx.lifecycle.LiveData
import androidx.room.*
import net.merayen.kitchentimer.data.Task

@Dao
interface TaskDao {
	@Query("SELECT * FROM Task WHERE id IN (:ids)")
	fun loadByIds(ids: IntArray): LiveData<List<Task>>

	@Query("SELECT * FROM Task WHERE id = :id")
	fun get(id: Int): LiveData<Task>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun save(task: Task)

	@Query("DELETE FROM Task")
	fun deleteAll()
}