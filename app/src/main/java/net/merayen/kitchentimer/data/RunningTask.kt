package net.merayen.kitchentimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * A task that is running, meaning it is shown in the WorkbenchTab, for what the user is currently doing.
 * @property id You know what this is and this is pointless documentation about that property
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
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("task")
        )
    ]
)
data class RunningTask(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(index = true) val task: Int?,
    val name: String,
    val start: Long = 0L,
    val elapsed: Int = 0,
    val seconds: Int = 0
)