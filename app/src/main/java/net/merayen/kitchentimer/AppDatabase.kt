package net.merayen.kitchentimer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import net.merayen.kitchentimer.data.*
import net.merayen.kitchentimer.queries.ItemDao
import net.merayen.kitchentimer.queries.TaskDao

@Database(
    entities = [
        Task::class,
        TaskReference::class,
        Tool::class,
        ToolsRequired::class,
        ToolProperty::class,
        Item::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        @Volatile
        private var db: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            var db = db
            if (db != null)
                return db

            synchronized(this) {
                val newDb = Room.databaseBuilder(context, AppDatabase::class.java, "General")
                    .fallbackToDestructiveMigration()
                    .build()
                this.db = newDb
                return newDb
            }
        }
    }

    abstract fun taskDao(): TaskDao
    abstract fun itemDao(): ItemDao
}