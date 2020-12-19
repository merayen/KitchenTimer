package net.merayen.kitchentimer.fragments.tabs.workbench

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels

import net.merayen.kitchentimer.data.RunningTimer
import net.merayen.kitchentimer.viewmodels.RunningTimerViewModel

class RunningTimerFragment(val runningTimerId: Int) : Fragment() {
	companion object {
		fun newInstance(runningTimerId: Int) = RunningTimerFragment(runningTimerId)
	}

	private var lastRunningTimer: RunningTimer? = null

	private val viewModel by viewModels<RunningTimerViewModel>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		//val binding = RunningTimerFragmentB
		val binding = net.merayen.kitchentimer.databinding.RunningTimerFragmentBinding.inflate(LayoutInflater.from(context))
		val view = binding.root

		viewModel.getRunningTimer(runningTimerId).observe(viewLifecycleOwner) {
			lastRunningTimer = it
		}

		binding.pause.setOnClickListener {
			val runningTimer = lastRunningTimer
			TODO()
		}

		fun update() {
			val runningTimer = lastRunningTimer
			if (runningTimer != null) {
				val percentage = runningTimer.progress * 100

				binding.name.text = "quick timer" // TODO use name from task
				binding.remaining.text = formatDuration(runningTimer.remaining)
				binding.total.text = formatDuration(runningTimer.seconds)
				binding.progressMarked.layoutParams = LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT,
					100 - percentage
				)
				binding.progressEmpty.layoutParams = LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT,
					percentage
				)
			}
			view.postDelayed({ update() }, 500)
		}

		view.post {
			update()
		}

		return view
	}

	private fun formatDuration(seconds: Int) = "${seconds / 3600}h ${seconds / 60 % 60}m ${seconds % 60}s"
}
