package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.Recipe
import net.merayen.kitchentimer.viewmodels.TaskSetupTabViewModel

class RecipesTab : Fragment() {
    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(recipeId: Int)
    }

    inner class TaskListRecyclerViewAdapter(
        private val mListener: OnListFragmentInteractionListener
    ) : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.task_setup_tab_item_fragment, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if (position >= items.size)
                return

            val data = items[position]

            holder.mView.findViewById<TextView>(R.id.name).text = data.name

            with(holder.mView) {
                setOnClickListener {
                    mListener.onListFragmentInteraction(data.id)
                }
            }
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

    companion object {
        fun newInstance() = RecipesTab()
    }

    internal val viewModel by viewModels<TaskSetupTabViewModel>()

    private var items: List<Recipe> = ArrayList()

    private val recipeFrame = RecipeFrame(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.recipe_tab_fragment, container, false)

        val taskList = view.findViewById<RecyclerView>(R.id.taskList)
        with(taskList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = TaskListRecyclerViewAdapter(object : OnListFragmentInteractionListener {
                override fun onListFragmentInteraction(recipeId: Int) {
                    recipeFrame.edit(recipeId)
                }
            })
        }

        viewModel.getRecipes().observe(viewLifecycleOwner, Observer {
            this.items = it
            taskList.adapter?.notifyDataSetChanged()
        })

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