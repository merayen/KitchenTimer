package net.merayen.kitchentimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A task represents a task in the kitchen. Like peeling potatoes.
 * @property id The unique id of this task
 * @property name The user friendly name of the task
 * @property duration How many seconds this task requires
 */
@Entity
data class Task(
	@PrimaryKey val id: Int,
	@ColumnInfo val name: String,
	@ColumnInfo val duration: Int // TODO remove? Should the tool (chef + equipment) define the duration?
)
