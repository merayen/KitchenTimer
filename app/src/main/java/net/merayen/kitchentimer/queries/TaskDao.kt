package net.merayen.kitchentimer.queries

import androidx.room.Dao
import androidx.room.Query
import net.merayen.kitchentimer.data.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task WHERE id IN (:ids)")
    fun loadByIds(ids: IntArray): List<Task>
}