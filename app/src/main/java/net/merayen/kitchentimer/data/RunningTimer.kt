package net.merayen.kitchentimer.data

import androidx.room.*

/**
 * A task that is running, meaning it is shown in the WorkbenchTab, for what the user is currently doing.
 * @property runningTimerId You know what this is and this is pointless documentation about that property
 * @property task The task that is being run. null if it is a Quick Timer
 * @property name Name of the owner
 * @property start Unix time in seconds when the timer got started. If less than 1, the timer is not active (e.g paused or not started yet) (e.g paused or not started yet)
 * @property elapsed Total time the task has run (add current time minus "start" if "start" is more than 0 to get total)
 * @property seconds How many seconds this timer will last
 */
@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = arrayOf("taskId"),
            childColumns = arrayOf("task")
        )
    ]
)
data class RunningTimer(
    @PrimaryKey(autoGenerate = true) val runningTimerId: Int,
    @ColumnInfo(index = true) val task: Int?,
    val name: String,
    var start: Long = 0L,
    var elapsed: Int = 0,
    val seconds: Int = 0,
) {
    fun pause() {
        if (start < 1L) return // Already paused
        elapsed += (System.currentTimeMillis() / 1000 - start).toInt()
    }

    val remaining: Int
        get() = if (start > 0)
            (seconds - (System.currentTimeMillis() / 1000 - start + elapsed)).toInt()
        else
            seconds - elapsed

    val progress: Float
        get() = if (start > 0)
            (elapsed + System.currentTimeMillis() / 1000 - start) / seconds.toFloat()
        else
            elapsed / seconds.toFloat()
}