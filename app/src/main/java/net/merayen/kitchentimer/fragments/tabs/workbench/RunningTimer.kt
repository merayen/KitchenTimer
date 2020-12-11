package net.merayen.kitchentimer.fragments.tabs.workbench

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.running_timer_fragment.*

import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.livedata.RunningTimerData
import net.merayen.kitchentimer.viewmodels.RunningTimerViewModel

class RunningTimer(val runningTimerId: Int) : Fragment() {
    companion object {
        fun newInstance(runningTimerId: Int) = RunningTimer(runningTimerId)
    }

    private var lastRunningTimer: RunningTimerData? = null

    private val viewModel by viewModels<RunningTimerViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.running_timer_fragment, container, false)

        viewModel.getRunningTimer(runningTimerId).observe(viewLifecycleOwner) {
            lastRunningTimer = it
        }

        view.findViewById<Button>(R.id.pause).setOnClickListener {
            val runningTimer = lastRunningTimer
            runningTimer
        }

        fun update() {
            val view = this.view!!
            val runningTimer = lastRunningTimer
            if (runningTimer != null) {
                val percentage = runningTimer.progress * 100

                view.findViewById<TextView>(R.id.name).text = runningTimer.taskName ?: "quick timer"
                view.findViewById<TextView>(R.id.remaining).text = formatDuration(runningTimer.remaining)
                view.findViewById<TextView>(R.id.total).text = formatDuration(runningTimer.runningTimerSeconds)
                view.findViewById<View>(R.id.progress_marked).layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    100 - percentage
                )
                view.findViewById<View>(R.id.progress_empty).layoutParams = LinearLayout.LayoutParams(
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
