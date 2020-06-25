package net.merayen.kitchentimer.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_setup_tab_fragment.view.*

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.WorkbenchTabViewModel

class WorkbenchTab : Fragment() {
    /**
     * Set this to make this view show a running task
     */
    var showRunningTaskId: Int? = null

    companion object {
        fun newInstance() = WorkbenchTab()
    }

    private lateinit var viewModel: WorkbenchTabViewModel

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
    }

    fun selectTask(taskInstance: Int) { // TODO should be some kind of task instance?
        showRunningTaskId = taskInstance
    }
}
