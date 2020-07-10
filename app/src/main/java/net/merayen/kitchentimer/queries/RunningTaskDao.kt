package net.merayen.kitchentimer.queries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.RunningTask
import net.merayen.kitchentimer.livedata.RunningTaskData

@Dao
interface RunningTaskDao {
    @Query(
        """
        SELECT
            r.id AS runningTaskId,
            r.start AS runningTaskStart,
            r.elapsed AS runningTaskElapsed,
            t.id AS taskId,
            t.name AS taskName
        FROM RunningTask r
        JOIN Task t
        WHERE r.task = t.id
        """
    )
    fun getWithTasks(): LiveData<List<RunningTaskData>>

    @Query("SELECT * FROM RunningTask WHERE id = (:ids)")
    fun get(ids: List<Int>): LiveData<List<RunningTask>>

    @Query("SELECT * FROM RunningTask WHERE id = :id")
    fun get(id: Int): LiveData<RunningTask>

    @Query("SELECT * FROM RunningTask")
    fun get(): LiveData<List<RunningTask>>

    @Query(
        """
        SELECT
            r.id AS runningTaskId,
            r.start AS runningTaskStart,
            r.elapsed AS runningTaskElapsed,
            t.id AS taskId,
            t.name AS taskName
        FROM RunningTask r
        JOIN Task t ON r.task = t.id
        WHERE r.id = :id
        """
    )
    fun getRunningTaskData(id: Int): LiveData<RunningTaskData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(runningTask: RunningTask)
}