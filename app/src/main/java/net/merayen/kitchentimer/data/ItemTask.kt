package net.merayen.kitchentimer.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * The task needed to create the item.
 *
 * When empty for an ingredient, it can be created by this task.
 */
@Entity(
	foreignKeys = [
		ForeignKey(
			entity = Item::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("item")
		),
		ForeignKey(
			entity = Task::class,
			parentColumns = arrayOf("taskId"),
			childColumns = arrayOf("task")
		),
	]
)
data class ItemTask(
	@PrimaryKey(autoGenerate = true) val id: Int,
	val item: Int,
	val task: Int,
)
