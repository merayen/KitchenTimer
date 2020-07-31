package net.merayen.kitchentimer.queries

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import net.merayen.kitchentimer.data.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipe WHERE id = :id")
    fun get(id: Int): LiveData<Recipe>

    @Query("SELECT * FROM Recipe")
    fun get(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(recipe: Recipe)
}