package net.merayen.kitchentimer.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.WorkbenchTabViewModel

class WorkbenchTab : Fragment() {
    class InitData {
        /**
         * Set this to make this view show a running task
         */
        var showRunningTaskId: Int? = null
    }

    class TaskListRecyclerViewAdapter : RecyclerView.Adapter<ViewHolder>() {
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
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            TODO("Not yet implemented")
        }
    }

    class ViewHolder(private val mView: View) : RecyclerView.ViewHolder(mView) {
        //private val mContentView: TextView = mView.content

        override fun toString(): String {
            return "hohohoho" //super.toString() + " '" + mContentView.text + "'"
        }
    }

    var showRunningTaskId: Int? = null
    companion object {
        fun newInstance() = WorkbenchTab()
    }

    private lateinit var viewModel: WorkbenchTabViewModel

    var initData: InitData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.workbench_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WorkbenchTabViewModel::class.java)
        // TODO: Use the ViewModel

        val showRunningTaskId = showRunningTaskId
        if (showRunningTaskId != null) {
            val noe = view
            val textView = noe!!.findViewById<TextView>(R.id.debugText)
            textView.text = "Should show TaskInstance $showRunningTaskId"

            this.showRunningTaskId = null
        }

        println("WorkbenchTab test number: ${viewModel.test}")
    }

    fun selectTask(taskInstance: Int) { // TODO should be some kind of task instance?
        showRunningTaskId = taskInstance
    }
}
