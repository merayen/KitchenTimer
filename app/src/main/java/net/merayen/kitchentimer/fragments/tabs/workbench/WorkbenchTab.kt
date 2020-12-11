package net.merayen.kitchentimer.fragments.tabs.workbench

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.livedata.RunningTimerData
import net.merayen.kitchentimer.viewmodels.WorkbenchTabViewModel

class WorkbenchTab : Fragment() {
    private val viewModel by viewModels<WorkbenchTabViewModel>()
    private var listener: RunningTimersListFragment.OnListFragmentInteractionListener? = null

    class RunningTimerListRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
        private var items: List<RunningTimerData> = ArrayList()

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

        fun setItems(items: List<RunningTimerData>) {
            this.items = items
        }
    }

    class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        //private val mContentView: TextView = mView.content

        override fun toString(): String {
            return "hohohoho" //super.toString() + " '" + mContentView.text + "'"
        }
    }

    var showRunningTimerId: Int? = null

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
            val adapter = RunningTimerListRecyclerViewAdapter()
            this.adapter = adapter

            viewModel.runningTasks.observe(viewLifecycleOwner, Observer {
                adapter.setItems(it)
            })
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val showRunningTimerId = showRunningTimerId
        if (showRunningTimerId != null) { // TODO move out in a separate method? Trigger it from item clicked on the left
            // TODO tissue:show_running_timer just do it
            val frameLayout = view!!.findViewById<FrameLayout>(R.id.current_timer)

            val newFrame = RunningTimer(showRunningTimerId)

            parentFragmentManager.commit {
                if (!frameLayout.isEmpty()) {
                    frameLayout.removeAllViews()
                    //replace(R.id.current_timer, newFrame)
                }

                add(R.id.current_timer, newFrame)

                viewModel.get(showRunningTimerId).observe(viewLifecycleOwner) {
                    //if (it != null) {
                    //    textView.text = it.taskName
                    //} else {
                    //    println("$showRunningTimerId not found :( ")
                    //}
                }
            }

            this.showRunningTimerId = null
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is RunningTimersListFragment.OnListFragmentInteractionListener) {
            listener = context
        }
    }

    fun selectTask(taskInstance: Int) { // TODO should be some kind of task instance?
        showRunningTimerId = taskInstance
    }
}