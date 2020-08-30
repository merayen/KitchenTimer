package net.merayen.kitchentimer.repositories

import net.merayen.kitchentimer.data.Recipe
import net.merayen.kitchentimer.dao.RecipeDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val recipeDao: RecipeDao
) {
    fun save(recipe: Recipe) = recipeDao.save(recipe)
    fun get(id: Int) = recipeDao.get(id)
    fun get() = recipeDao.get()
}