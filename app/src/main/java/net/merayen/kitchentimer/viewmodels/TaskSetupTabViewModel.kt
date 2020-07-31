package net.merayen.kitchentimer.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import net.merayen.kitchentimer.AppDatabase
import net.merayen.kitchentimer.data.Task
import net.merayen.kitchentimer.repositories.RecipeRepository
import net.merayen.kitchentimer.repositories.TaskRepository

class TaskSetupTabViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(AppDatabase.getDatabase(application).taskDao())
    private val recipeRepository = RecipeRepository(AppDatabase.getDatabase(application).recipeDao())

    fun getRecipes() = recipeRepository.get()
    fun getRecipe(id: Int) = recipeRepository.get(id)
}
