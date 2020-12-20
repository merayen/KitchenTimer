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
		RunningTimer::class,
		Item::class,
		ItemInstance::class,
		ItemInstanceProperty::class,
		ItemInstanceLocation::class,
		ItemTask::class,
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

					db.itemDao().save(Item(name = "Kjeler"))
					db.itemDao().save(Item(name = "Ingredienser"))
					db.itemDao().save(Item(parent = 1, name = "Stekepanner"))
					db.itemDao().save(Item(parent = 3, name = "Karbon"))
					db.itemDao().save(Item(parent = 3, name = "Rustfritt"))
					db.itemDao().save(Item(parent = 2, name = "Væske"))
					db.itemDao().save(Item(parent = 6, name = "Vann"))
					db.itemDao().save(Item(parent = 2, name = "Grønnsaker"))
					db.itemDao().save(Item(parent = 2, name = "Potet"))
					db.itemDao().save(Item(parent = 9, name = "Mandel"))
					db.itemDao().save(Item(parent = 9, name = "Bakepotet"))
					db.itemDao().save(Item(parent = 9, name = "Amandine"))
					db.itemDao().save(Item(parent = 9, name = "Franceline"))
					db.itemDao().save(Item(parent = 9, name = "Anoe"))
					db.itemDao().save(Item(parent = 9, name = "Vildemose"))
					db.itemDao().save(Item(parent = 9, name = "Ringerikspotet"))
					db.itemDao().save(Item(parent = 9, name = "Mandel"))
					db.itemDao().save(Item(parent = 9, name = "Fjellmandel"))
					db.itemDao().save(Item(parent = 9, name = "Berber"))
					db.itemDao().save(Item(parent = 9, name = "Gulløye"))
					db.itemDao().save(Item(parent = 9, name = "Asterix"))
					db.itemDao().save(Item(parent = 9, name = "Pimpernel"))
					db.itemDao().save(Item(parent = 9, name = "Kerrs pink"))
					db.itemDao().save(Item(parent = 9, name = "Blå kongo"))


					db.itemInstanceDao().save(ItemInstance(1, 5, "Kjele"))
					db.itemInstanceDao().save(ItemInstance(2, 5, "Sautekjele"))

					db.itemInstanceDao().save(ItemInstance(3, 4, "Karbonstålpanne 20cm"))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(1, 3, ItemInstanceProperty.Type.RADIUS.name, 200f))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(2, 3, ItemInstanceProperty.Type.HEIGHT.name, 40f))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(3, 3, ItemInstanceProperty.Type.VOLUME.name, 1000f))

					db.itemInstanceDao().save(ItemInstance(5, 4, "Karbonstålpanne 24cm"))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(4, 5, ItemInstanceProperty.Type.RADIUS.name, 240f))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(5, 5, ItemInstanceProperty.Type.HEIGHT.name, 40f))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(6, 5, ItemInstanceProperty.Type.VOLUME.name, 1200f))

					db.itemInstanceDao().save(ItemInstance(6, 4, "Karbonstålpanne 28cm"))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(7, 6, ItemInstanceProperty.Type.RADIUS.name, 280f))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(8, 6, ItemInstanceProperty.Type.HEIGHT.name, 40f))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(9, 6, ItemInstanceProperty.Type.VOLUME.name, 1400f))

					db.itemInstanceDao().save(ItemInstance(4, 7, "Vann fra springen"))
					db.itemInstancePropertyDao()
						.save(ItemInstanceProperty(10, 4, ItemInstanceProperty.Type.VOLUME.name, Float.MAX_VALUE))

					db.taskDao().save(Task(1, "Koke poteter"))
					db.taskDao().save(Task(2, "Hente agurk"))
					db.taskDao().save(Task(3, "Skrelle poteter"))

					db.taskDao().save(Task(10, "Steke pannekaker"))
					db.taskDao().save(Task(11, "Varme teppan"))
					db.taskDao().save(Task(12, "Bland ingredienser"))
					db.taskDao().save(Task(13, "Pisk alt sammen"))
					db.taskDao().save(Task(14, "La røren svelle"))

					db.taskDao().saveDependency(10, 11)
					db.taskDao().saveDependency(10, 14)
					db.taskDao().saveDependency(13, 12)


					db.recipeDao().save(
						Recipe(
							1,
							"Pannekaker",
							"Fantastisk gode pannekaker med en touch av hvetemel og egg. Kokkens anbefaling.",
							10
						)
					)

					db.runningTaskDao()
						.save(RunningTimer(1, 1, "Koke poteter", start = System.currentTimeMillis() / 1000, seconds = 20 * 60))
					db.runningTaskDao()
						.save(RunningTimer(2, 11, "Varme teppan", start = System.currentTimeMillis() / 1000, seconds = 15 * 60))

					// Storage
					db.locationDao().save(Location(name = "Kjøleskap"))
					db.locationDao().save(Location(parent = 1, name = "Hylle 1"))
					db.locationDao().save(Location(parent = 1, name = "Hylle 2"))
					db.locationDao().save(Location(parent = 1, name = "Hylle 3"))
					db.locationDao().save(Location(name = "Fryser"))
					db.locationDao().save(Location(parent = 5, name = "A1"))

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

		fun getInstance(): AppDatabase = INSTANCE!!
	}

	abstract fun taskDao(): TaskDao
	abstract fun itemDao(): ItemDao
	abstract fun itemInstanceDao(): ItemInstanceDao
	abstract fun itemInstanceLocationDao(): ItemInstanceLocationDao
	abstract fun itemInstancePropertyDao(): ItemInstancePropertyDao
	abstract fun runningTaskDao(): RunningTimerDao
	abstract fun recipeDao(): RecipeDao
	abstract fun locationDao(): LocationDao
}
