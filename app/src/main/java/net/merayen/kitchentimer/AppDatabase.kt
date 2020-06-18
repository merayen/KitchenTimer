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
    private class Callback : RoomDatabase.Callback() {
        override fun onOpen(database: SupportSQLiteDatabase) {
            super.onOpen(database)

            INSTANCE.let { db ->
                GlobalScope.launch {
                    db!!
                    db.itemDao().save(Item(1, 0, "Kjele"))
                    db.itemDao().save(Item(2, 0, "Stekepanne"))
                    db.itemDao().save(Item(3, 0, "Vann"))
                    db.itemDao().save(Item(4, 0, "Potet"))

                    db.taskDao().save(Task(1, "Koke poteter"))
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
}