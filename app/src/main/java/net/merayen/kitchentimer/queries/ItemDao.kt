package net.merayen.kitchentimer.queries

import androidx.room.Query
import net.merayen.kitchentimer.data.Task

interface ItemDao {
    @Query("SELECT * FROM Item WHERE id IN (:ids)")
    fun loadAll(ids: IntArray): List<Task>
}