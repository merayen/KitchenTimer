package net.merayen.kitchentimer.fragments.tabs.workbench

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.RunningTimerViewModel

class RunningTimer : Fragment() { // TODO delete? As MyRunningTimersListRecycler… seems to do this?
    companion object {
        fun newInstance() = RunningTimer()
    }

    private val viewModel by viewModels<RunningTimerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.running_timer_fragment, container, false)
    }
}
