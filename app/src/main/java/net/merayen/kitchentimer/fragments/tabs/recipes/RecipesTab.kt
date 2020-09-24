package net.merayen.kitchentimer.fragments.tabs.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayout

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.fragments.common.SelectableList
import net.merayen.kitchentimer.viewmodels.TaskSetupTabViewModel

class RecipesTab : Fragment() {
    companion object {
        fun newInstance() = RecipesTab()
    }

    internal val viewModel by viewModels<TaskSetupTabViewModel>()

    private val recipeFrame = RecipeFrame(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_tab_fragment, container, false)

        val tabs = view.findViewById<TabLayout>(R.id.recipe_edit_tabs)
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {}
            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                recipeFrame.showTab(tab?.position ?: return)
            }
        })

        return view
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        if (childFragment is SelectableList) {
            viewModel.getRecipes().observe(viewLifecycleOwner, Observer {
                childFragment.applyData(it)
            })
        }
    }

    val tabs: TabLayout?
        get() = view?.findViewById(R.id.recipe_edit_tabs)
}

private class RecipeFrame(private val recipesTab: RecipesTab) {
    val tabs: TabLayout?
        get() = recipesTab.tabs

    fun edit(recipe: Int) {
        recipesTab.viewModel.getRecipe(recipe).observe(recipesTab.viewLifecycleOwner, Observer {
            println("Supposed to edit this one: $it")
        })
    }

    fun showTab(position: Int) {
        val tabs = tabs!!

        // TODO Select the correct FrameLayout based on the tab chosen
    }
}