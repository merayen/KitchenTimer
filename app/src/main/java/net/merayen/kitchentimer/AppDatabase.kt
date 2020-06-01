package net.merayen.kitchentimer

import androidx.room.Database
import androidx.room.RoomDatabase
import net.merayen.kitchentimer.data.*
import net.merayen.kitchentimer.queries.TaskDao

@Database(
	entities = [
		Task::class,
		TaskReference::class,
		Tool::class,
		ToolsRequired::class,
		ToolProperty::class
	],
	version = 1,
	exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
	abstract fun taskDao(): TaskDao
}