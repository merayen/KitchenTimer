package net.merayen.kitchentimer.fragments

import android.content.Context
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

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.livedata.RunningTaskData
import net.merayen.kitchentimer.viewmodels.WorkbenchTabViewModel

class WorkbenchTab : Fragment() {
    private val viewModel by viewModels<WorkbenchTabViewModel>()
    private var listener: RunningTimersListFragment.OnListFragmentInteractionListener? = null

    class RunningTaskListRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
        private var items: List<RunningTaskData> = ArrayList()

        private val mOnClickListener: View.OnClickListener
        var onItemClick: (() -> Unit)? = null

        init {
            mOnClickListener = View.OnClickListener {
                onItemClick?.invoke()
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_workbench_task_listitem, parent, false)

            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.mView.findViewById<TextView>(R.id.name).text = items[position].taskName
        }

        fun setItems(items: List<RunningTaskData>) {
            this.items = items
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //private val mContentView: TextView = mView.content

        override fun toString(): String {
            return "hohohoho" //super.toString() + " '" + mContentView.text + "'"
        }
    }

    var showRunningTaskId: Int? = null

    companion object {
        fun newInstance() = WorkbenchTab()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.workbench_tab_fragment, container, false)

        // Set the adapter on the task item list
        val taskList = view.findViewById<RecyclerView>(R.id.taskList)
        with(taskList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            val adapter = RunningTaskListRecyclerViewAdapter()
            this.adapter = adapter

            viewModel.runningTasks.observe(viewLifecycleOwner, Observer {
                adapter.setItems(it)
            })
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val showRunningTaskId = showRunningTaskId
        if (showRunningTaskId != null) { // TODO move out in a separate method? Trigger it from item clicked on the left
            val noe = view
            val textView = noe!!.findViewById<TextView>(R.id.debugText)

            println("RunningTask.id=$showRunningTaskId")
            viewModel.get(showRunningTaskId).observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    textView.text = it.taskName
                } else {
                    println("$showRunningTaskId not found :( ")
                }
            })

            this.showRunningTaskId = null
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is RunningTimersListFragment.OnListFragmentInteractionListener) {
            listener = context
        }
    }

    fun selectTask(taskInstance: Int) { // TODO should be some kind of task instance?
        showRunningTaskId = taskInstance
    }
}
