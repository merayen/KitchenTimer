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

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.data.RunningTask
import net.merayen.kitchentimer.viewmodels.WorkbenchTabViewModel

class WorkbenchTab : Fragment() {
    private val viewModel by viewModels<WorkbenchTabViewModel>()

    class RunningTaskListRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
        var runningTasks: List<RunningTask>? = ArrayList()

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
            return 3
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.mView.findViewById<TextView>(R.id.name).text = arrayOf(
                "Skrell poteter",
                "Stek kjøttkaker",
                "Kok poteter"
            )[position]
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
            adapter = RunningTaskListRecyclerViewAdapter()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val showRunningTaskId = showRunningTaskId
        if (showRunningTaskId != null) {
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

    fun selectTask(taskInstance: Int) { // TODO should be some kind of task instance?
        showRunningTaskId = taskInstance
    }
}
