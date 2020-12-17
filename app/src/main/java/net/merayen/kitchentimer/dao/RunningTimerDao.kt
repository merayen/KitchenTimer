package net.merayen.kitchentimer.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.livedata.RunningTimerWithTask

@Dao
interface RunningTimerDao {
    @Query("SELECT * FROM RunningTimer WHERE id = (:ids)")
    fun get(ids: List<Int>): LiveData<List<RunningTimer>>

    @Query("SELECT * FROM RunningTimer WHERE id = :id")
    fun get(id: Int): LiveData<RunningTimer>

    @Query("SELECT * FROM RunningTimer")
    fun get(): LiveData<List<RunningTimer>>

    @Query("SELECT RunningTimer.*, Task.* FROM RunningTimer LEFT JOIN Task WHERE RunningTimer.task = Task.id AND RunningTimer.id = :id")
    fun getWithTask(id: Int): LiveData<RunningTimerWithTask>

    @Query("SELECT RunningTimer.*, Task.* FROM RunningTimer LEFT JOIN Task WHERE RunningTimer.task = Task.id")
    fun getWithTask(): LiveData<List<RunningTimerWithTask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(runningTimer: RunningTimer)
}