package net.merayen.kitchentimer.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.TaskSetupTabViewModel

class TaskSetupTab : Fragment() {

    companion object {
        fun newInstance() = TaskSetupTab()
    }

    private lateinit var viewModel: TaskSetupTabViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_setup_tab_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TaskSetupTabViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
