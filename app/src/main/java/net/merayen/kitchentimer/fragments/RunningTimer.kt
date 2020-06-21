package net.merayen.kitchentimer.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import net.merayen.kitchentimer.R

class RunningTimer : Fragment() { // TODO delete? As MyRunningTimersListRecycler… seems to do this?

    companion object {
        fun newInstance() = RunningTimer()
    }

    private lateinit var viewModel: RunningTimerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.running_timer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RunningTimerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
