package net.merayen.kitchentimer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.merayen.kitchentimer.data.*
import net.merayen.kitchentimer.queries.ItemDao
import net.merayen.kitchentimer.queries.RunningTaskDao
import net.merayen.kitchentimer.queries.TaskDao

@Database(
    entities = [
        Task::class,
        TaskDependency::class,
        RunningTask::class,
        Item::class,
        ItemInstance::class,
        ItemInstanceProperty::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    private class Callback : RoomDatabase.Callback() {
        override fun onOpen(database: SupportSQLiteDatabase) {
            INSTANCE.let { db ->
                GlobalScope.launch {
                    db!!
                    db.itemDao().save(Item(1, 0, "Kjele"))
                    db.itemDao().save(Item(2, 0, "Stekepanne"))
                    db.itemDao().save(Item(3, 0, "Vann"))
                    db.itemDao().save(Item(4, 0, "Potet"))

                    db.taskDao().save(Task(1, "Koke poteter"))
                    db.taskDao().save(Task(2, "Hente agurk"))
                    db.taskDao().save(Task(3, "Skrelle poteter"))
                    db.taskDao().save(Task(4, "Varme teppan"))
                    db.taskDao().save(Task(5, "Steke pannekaker"))

                    db.runningTaskDao().save(RunningTask(1, 1, elapsed = 83))
                    db.runningTaskDao().save(RunningTask(2, 4, elapsed = 343))
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                context.deleteDatabase("General")
                val newDb = Room.databaseBuilder(context, AppDatabase::class.java, "General")
                    .addCallback(Callback())
                    .build()
                INSTANCE = newDb

                newDb
            }
        }
    }

    abstract fun taskDao(): TaskDao
    abstract fun itemDao(): ItemDao
    abstract fun runningTaskDao(): RunningTaskDao
}