package net.merayen.kitchentimer.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


/**
 * Links tasks together.
 * @property id The unique id of this link
 * @property task The task owning the link
 * @property child The task that the owning task contains
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
class TaskReference(
	@PrimaryKey val id: Int,
	@ColumnInfo(index = true) val task: Int,
	@ColumnInfo val child: Int
)