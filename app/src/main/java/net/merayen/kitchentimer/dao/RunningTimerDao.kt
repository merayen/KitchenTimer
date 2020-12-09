package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.livedata.RunningTimerData

@Dao
interface RunningTimerDao {
    @Query(
        """
        SELECT
            r.id AS runningTimerId,
            r.start AS runningTimerStart,
            r.elapsed AS runningTimerElapsed,
            t.id AS taskId,
            t.name AS taskName
        FROM RunningTimer r
        LEFT JOIN Task t ON t.id = r.task
        """
    )
    fun getRunningTimerData(): LiveData<List<RunningTimerData>>

    @Query("SELECT * FROM RunningTimer WHERE id = (:ids)")
    fun get(ids: List<Int>): LiveData<List<RunningTimer>>

    @Query("SELECT * FROM RunningTimer WHERE id = :id")
    fun get(id: Int): LiveData<RunningTimer>

    @Query("SELECT * FROM RunningTimer")
    fun get(): LiveData<List<RunningTimer>>

    @Query(
        """
        SELECT
            r.id AS runningTimerId,
            r.start AS runningTimerStart,
            r.elapsed AS runningTimerElapsed,
            t.id AS taskId,
            t.name AS taskName
        FROM RunningTimer r
        JOIN Task t ON r.task = t.id
        WHERE r.id = :id
        """
    )
    fun getRunningTimerData(id: Int): LiveData<RunningTimerData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(runningTimer: RunningTimer)
}