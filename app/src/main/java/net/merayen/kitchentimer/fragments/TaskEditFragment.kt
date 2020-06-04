package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.TaskEditViewModel

class TaskEditFragment : Fragment() {

    companion object {
        fun newInstance() = TaskEditFragment()
    }

    private lateinit var viewModel: TaskEditViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_edit_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(TaskEditViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
