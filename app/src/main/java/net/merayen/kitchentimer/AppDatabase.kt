package net.merayen.kitchentimer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.merayen.kitchentimer.data.*
import net.merayen.kitchentimer.dao.*
import net.merayen.kitchentimer.data.ItemInstance

@Database(
    entities = [
        Task::class,
        TaskDependency::class,
        RunningTask::class,
        Item::class,
        ItemInstance::class,
        ItemInstanceProperty::class,
        ItemInstanceLocation::class,
        Recipe::class,
        Location::class
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

                    db.itemInstanceDao().save(ItemInstance(1, 1, "Kokekjele"))
                    db.itemInstanceDao().save(ItemInstance(2, 1, "Sautekjele"))

                    db.itemInstanceDao().save(ItemInstance(3, 2, "Karbonstålpanne 24cm"))
                    db.itemInstancePropertyDao().save(ItemInstanceProperty(1, ItemInstanceProperty.Type.RADIUS.name, 240f))
                    db.itemInstancePropertyDao().save(ItemInstanceProperty(2, ItemInstanceProperty.Type.HEIGHT.name, 40f))
                    db.itemInstancePropertyDao().save(ItemInstanceProperty(3, ItemInstanceProperty.Type.VOLUME.name, 1000f))

                    db.itemInstanceDao().save(ItemInstance(4, 3, "Vann fra springen"))
                    db.itemInstancePropertyDao().save(ItemInstanceProperty(4, ItemInstanceProperty.Type.VOLUME.name, Float.MAX_VALUE))

                    db.taskDao().save(Task(1, "Koke poteter"))
                    db.taskDao().save(Task(2, "Hente agurk"))
                    db.taskDao().save(Task(3, "Skrelle poteter"))
                    db.taskDao().save(Task(4, "Varme teppan"))
                    db.taskDao().save(Task(5, "Steke pannekaker"))

                    db.taskDao().saveDependency(5, 4)

                    db.recipeDao().save(Recipe(1, "Pannekaker", "Fantastisk gode pannekaker med en touch av hvetemel og egg. Kokkens anbefaling.", 5))

                    db.runningTaskDao().save(RunningTask(1, 1, "Koke poteter", elapsed = 83))
                    db.runningTaskDao().save(RunningTask(2, 4, "Varme teppan", elapsed = 343))

                    // Storage
                    db.locationDao().save(Location(1, "Kjøleskap"))
                    db.locationDao().save(Location(2, "Hylle 1"))
                    db.locationDao().save(Location(3, "Hylle 2"))
                    db.locationDao().save(Location(4, "Hylle 3"))
                    db.locationDao().save(Location(5, "Fryser"))
                    db.locationDao().save(Location(6, "A1"))

                    // Putting items in the different locations
                    db.itemInstanceLocationDao()
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
    abstract fun itemInstanceDao(): ItemInstanceDao
    abstract fun itemInstanceLocationDao(): ItemInstanceLocationDao
    abstract fun itemInstancePropertyDao(): ItemInstancePropertyDao
    abstract fun runningTaskDao(): RunningTaskDao
    abstract fun recipeDao(): RecipeDao
    abstract fun locationDao(): LocationDao
}