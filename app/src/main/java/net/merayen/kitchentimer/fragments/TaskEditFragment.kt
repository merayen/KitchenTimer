package net.merayen.kitchentimer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.TaskEditViewModel

class TaskEditFragment : Fragment() {
    companion object {
        fun newInstance() = TaskEditFragment()
    }

    private val viewModel by viewModels<TaskEditViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.task_edit_fragment, container, false)
    }
}
