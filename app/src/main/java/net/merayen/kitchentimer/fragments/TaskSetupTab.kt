package net.merayen.kitchentimer.fragments

import android.content.ReceiverCallNotAllowedException
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginLeft
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.TaskSetupTabViewModel

class TaskSetupTab : Fragment() {
    class TaskListRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
        class DummyData(val name: String, val indent: Int)

        private val dummyData = arrayOf(
            DummyData("Pepperoni Pizza", 0),
            DummyData("Bland hvetemel", 1),
            DummyData("Tradisjonelle kjøttkaker", 0),
            DummyData("Okonomiyaki", 0),
            DummyData("Mix egg", 1),
            DummyData("Spicy hot mango paste chicken", 0)
        )

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.task_setup_tab_item_fragment, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dummyData.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = dummyData[position]

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(data.indent * 20, 0, 0, 0)
            holder.mView.layoutParams = params

            holder.mView.findViewById<TextView>(R.id.name).text = data.name
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView)

    companion object {
        fun newInstance() = TaskSetupTab()
    }

    private lateinit var viewModel: TaskSetupTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.task_setup_tab_fragment, container, false)

        val taskList = view.findViewById<RecyclerView>(R.id.taskList)
        with(taskList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = TaskListRecyclerViewAdapter()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskSetupTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
