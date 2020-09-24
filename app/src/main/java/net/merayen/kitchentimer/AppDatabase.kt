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

                    db.itemDao().save(Item(1, name = "Kjeler"))
                    db.itemDao().save(Item(2, 1, "Stekepanner"))
                    db.itemDao().save(Item(3, 2, "Karbon"))
                    db.itemDao().save(Item(24, 2, "Rustfritt"))
                    db.itemDao().save(Item(4, name = "Ingredienser"))
                    db.itemDao().save(Item(5, 4, "Væske"))
                    db.itemDao().save(Item(6, 5, "Vann"))
                    db.itemDao().save(Item(7, 4, "Grønnsaker"))
                    db.itemDao().save(Item(8, 7, "Potet"))
                    db.itemDao().save(Item(9, 8, "Mandel"))
                    db.itemDao().save(Item(10, 8, "Bakepotet"))
                    db.itemDao().save(Item(11, 8, "Amandine"))
                    db.itemDao().save(Item(12, 8, "Franceline"))
                    db.itemDao().save(Item(13, 8, "Anoe"))
                    db.itemDao().save(Item(14, 8, "Vildemose"))
                    db.itemDao().save(Item(15, 8, "Ringerikspotet"))
                    db.itemDao().save(Item(16, 8, "Mandel"))
                    db.itemDao().save(Item(17, 8, "Fjellmandel"))
                    db.itemDao().save(Item(18, 8, "Berber"))
                    db.itemDao().save(Item(19, 8, "Gulløye"))
                    db.itemDao().save(Item(20, 8, "Asterix"))
                    db.itemDao().save(Item(21, 8, "Pimpernel"))
                    db.itemDao().save(Item(22, 8, "Kerrs pink"))
                    db.itemDao().save(Item(23, 8, "Blå kongo"))


                    db.itemInstanceDao().save(ItemInstance(1, 1, "Kjele"))
                    db.itemInstanceDao().save(ItemInstance(2, 1, "Sautekjele"))

                    db.itemInstanceDao().save(ItemInstance(3, 3, "Karbonstålpanne 20cm"))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(1, 3, ItemInstanceProperty.Type.RADIUS.name, 200f))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(2, 3, ItemInstanceProperty.Type.HEIGHT.name, 40f))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(3, 3, ItemInstanceProperty.Type.VOLUME.name, 1000f))

                    db.itemInstanceDao().save(ItemInstance(5, 3, "Karbonstålpanne 24cm"))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(4, 5, ItemInstanceProperty.Type.RADIUS.name, 240f))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(5, 5, ItemInstanceProperty.Type.HEIGHT.name, 40f))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(6, 5, ItemInstanceProperty.Type.VOLUME.name, 1200f))

                    db.itemInstanceDao().save(ItemInstance(6, 3, "Karbonstålpanne 28cm"))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(7, 6, ItemInstanceProperty.Type.RADIUS.name, 280f))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(8, 6, ItemInstanceProperty.Type.HEIGHT.name, 40f))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(9, 6, ItemInstanceProperty.Type.VOLUME.name, 1400f))

                    db.itemInstanceDao().save(ItemInstance(4, 6, "Vann fra springen"))
                    db.itemInstancePropertyDao()
                        .save(ItemInstanceProperty(10, 4, ItemInstanceProperty.Type.VOLUME.name, Float.MAX_VALUE))

                    db.taskDao().save(Task(1, "Koke poteter"))
                    db.taskDao().save(Task(2, "Hente agurk"))
                    db.taskDao().save(Task(3, "Skrelle poteter"))
                    db.taskDao().save(Task(4, "Varme teppan"))
                    db.taskDao().save(Task(5, "Steke pannekaker"))

                    db.taskDao().saveDependency(5, 4)

                    db.recipeDao().save(
                        Recipe(
                            1,
                            "Pannekaker",
                            "Fantastisk gode pannekaker med en touch av hvetemel og egg. Kokkens anbefaling.",
                            5
                        )
                    )

                    db.runningTaskDao().save(RunningTask(1, 1, "Koke poteter", elapsed = 83))
                    db.runningTaskDao().save(RunningTask(2, 4, "Varme teppan", elapsed = 343))

                    // Storage
                    db.locationDao().save(Location(1, name = "Kjøleskap"))
                    db.locationDao().save(Location(2, 1, "Hylle 1"))
                    db.locationDao().save(Location(3, 1, "Hylle 2"))
                    db.locationDao().save(Location(4, 1, "Hylle 3"))
                    db.locationDao().save(Location(5, name = "Fryser"))
                    db.locationDao().save(Location(6, 5, "A1"))

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
