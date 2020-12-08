package net.merayen.kitchentimer.fragments.tabs.quicktimers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import net.merayen.kitchentimer.R
import net.merayen.kitchentimer.viewmodels.QuickTimersTabViewModel
import java.util.Observer
import kotlin.math.max

class QuickTimersTab : Fragment() {
    private val viewModel by viewModels<QuickTimersTabViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.quicktimers_tab_fragment, container, false)

        val addHour = view.findViewById<Button>(R.id.add_hour)
        addHour.setOnClickListener {
            updateStuff(3600)
        }

        val addMinute = view.findViewById<Button>(R.id.add_minute)
        addMinute.setOnClickListener {
            updateStuff(60)
        }

        val addMinutes = view.findViewById<Button>(R.id.add_minutes)
        addMinutes.setOnClickListener {
            updateStuff(60 * 5)
        }

        val subHour = view.findViewById<Button>(R.id.sub_hour)
        subHour.setOnClickListener {
            updateStuff(-3600)
        }

        val subMinute = view.findViewById<Button>(R.id.sub_minute)
        subMinute.setOnClickListener {
            updateStuff(-60)
        }

        val subMinutes = view.findViewById<Button>(R.id.sub_minutes)
        subMinutes.setOnClickListener {
            updateStuff(-60 * 5)
        }

        val start = view.findViewById<Button>(R.id.start)
        start.setOnClickListener {
            viewModel.createQuickTimer()
        }

        updateStuff(0, view)

        return view
    }

    private fun updateStuff(offset: Int, view: View? = this.view) {
        view!!

        viewModel.seconds = max(60, viewModel.seconds + offset)

        val seconds = viewModel.seconds
        view.findViewById<TextView>(R.id.hours)?.text = "${seconds / 3600}h"
        view.findViewById<TextView>(R.id.minutes)?.text = "${seconds / 60 % 60}m"
    }
}